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

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth/kakao")
public class OAuthController {

    private final KakaoOAuthService kakaoOAuthService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // application.properties 값 주입
    @Value("${kakao.client.id}")
    private String kakaoClientId;

    @Value("${kakao.redirect.uri}")
    private String kakaoRedirectUri;

    // 카카오 로그인 리다이렉트 URL
    @GetMapping("/login")
    public ResponseEntity<Void> redirectToKakaoLogin() {
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize"
                + "?response_type=code"
                + "&client_id=" + kakaoClientId
                + "&redirect_uri=" + kakaoRedirectUri;
        return ResponseEntity.status(302)
                .header("Location", kakaoAuthUrl)
                .build();
    }

    // 카카오 로그인 콜백
    @GetMapping("/callback")
    public ResponseEntity<String> kakaoCallback(@RequestParam String code, HttpServletResponse response) {
        // 1. 인가 코드로 토큰 발급
        KakaoTokenResponse tokenResponse = kakaoOAuthService.getAccessToken(code);

        // 2. Access Token으로 사용자 정보 조회
        KakaoUserInfo userInfo = kakaoOAuthService.getUserInfo(tokenResponse.accessToken());
        String nickname = userInfo.kakaoAccount().profile().nickname();
        String email = userInfo.kakaoAccount().email();

        // 3. DB에서 카카오 사용자 찾거나 없으면 신규 가입
        User user = userService.findOrCreateByKakaoId(userInfo.id(), nickname, email);

        // 4. JWT 발급
        String accessToken = jwtTokenProvider.createToken(user.getId());
        String refreshToken = jwtTokenProvider.createRefreshToken();
        userService.updateRefreshToken(user.getUsername(), refreshToken);

        // 5. 쿠키에 저장
        Cookie cookie = new Cookie("access_token", accessToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        return ResponseEntity.ok("카카오 로그인 성공! JWT 발급 완료");
    }
}