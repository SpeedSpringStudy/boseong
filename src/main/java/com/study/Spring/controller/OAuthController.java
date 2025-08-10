package com.study.Spring.controller;

import com.study.Spring.dto.KakaoTokenResponse;
import com.study.Spring.dto.KakaoUserInfo;
import com.study.Spring.entity.User;
import com.study.Spring.security.JwtTokenProvider;
import com.study.Spring.service.KakaoOAuthService;
import com.study.Spring.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth/kakao")
public class OAuthController {

    private final KakaoOAuthService kakaoOAuthService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${kakao.client.id}")
    private String kakaoClientId;

    @Value("${kakao.redirect.uri}")
    private String kakaoRedirectUri;

    @GetMapping("/login")
    public ResponseEntity<Void> redirectToKakaoLogin() {
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize"
                + "?response_type=code"
                + "&client_id=" + kakaoClientId
                + "&redirect_uri=" + kakaoRedirectUri;
        return ResponseEntity.status(302).header("Location", kakaoAuthUrl).build();
    }

    @GetMapping("/callback")
    public ResponseEntity<String> kakaoCallback(@RequestParam String code, HttpServletResponse response) {
        KakaoTokenResponse tokenResponse = kakaoOAuthService.getAccessToken(code);
        KakaoUserInfo userInfo = kakaoOAuthService.getUserInfo(tokenResponse.accessToken());
        String email = userInfo.kakaoAccount().email();
        User user = kakaoOAuthService.findOrCreateByKakao(userInfo.id(), email);

        String accessToken = jwtTokenProvider.createToken(user.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken();
        userService.updateRefreshToken(user.getEmail(), refreshToken);

        Cookie cookie = new Cookie("access_token", accessToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(tokenResponse.expiresIn() == null ? 3600 : tokenResponse.expiresIn());
        userService.updateKakaoTokens(user.getId(), tokenResponse.accessToken(), tokenResponse.refreshToken(), expiresAt);

        return ResponseEntity.ok("카카오 로그인 성공");
    }
}