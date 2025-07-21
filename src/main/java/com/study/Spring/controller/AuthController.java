package com.study.Spring.controller;

import com.study.Spring.dto.SignupRequestDto;
import com.study.Spring.security.JwtTokenProvider;
import com.study.Spring.service.UserService;
import com.study.Spring.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto request) {
        userService.register(request.username(), request.password());
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginInfo, HttpServletResponse response ) {
        String username = loginInfo.get("username");
        String password = loginInfo.get("password");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String accessToken = jwtTokenProvider.createToken(username);

        String refreshToken = jwtTokenProvider.createRefreshToken(username);
        userService.updateRefreshToken(username, refreshToken);

        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(60 * 60); // 1시간
        response.addCookie(accessTokenCookie);

        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String refreshToken = body.get("refreshToken");

        User user = userService.findByUsername(username);

//        System.out.println("DB token: " + user.getRefreshToken());
//        System.out.println("Req token: " + refreshToken);
//        System.out.println("equal? " + refreshToken.equals(user.getRefreshToken()));

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
        }

        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new IllegalArgumentException("토큰이 일치하지 않습니다.");
        }

        String newAccessToken = jwtTokenProvider.createToken(username);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }
}