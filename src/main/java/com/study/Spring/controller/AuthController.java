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

        User user = userService.findByUsername(username);
        Long userId = user.getId();

        String accessToken = jwtTokenProvider.createToken(userId);

        String refreshToken = jwtTokenProvider.createRefreshToken();
        userService.updateRefreshToken(username, refreshToken);

        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(60 * 60); // 1시간
        response.addCookie(accessTokenCookie);

        response.addHeader("Set-Cookie",
                "access_token=" + accessToken +
                        "; Max-Age=3600" +
                        "; Path=/" +
                        "; Secure" +
                        "; HttpOnly" +
                        "; SameSite=Strict");

        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refresh(HttpServletRequest request,
                                                       @RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");

        String accessToken = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("access_token")) {
                accessToken = cookie.getValue();
            }
        }
        if (accessToken == null) {
            throw new IllegalArgumentException("Access Token이 없습니다.");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(accessToken);
        User user = userService.findById(userId);

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
        }

        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new IllegalArgumentException("토큰이 일치하지 않습니다.");
        }

        String newAccessToken = jwtTokenProvider.createToken(userId);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }
}