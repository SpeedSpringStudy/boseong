package com.study.Spring.service;

import com.study.Spring.dto.KakaoTokenResponse;
import com.study.Spring.dto.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class KakaoOAuthService {

    @Value("${kakao.client.id}")
    private String clientId;

    @Value("${kakao.redirect.uri}")
    private String redirectUri;

    @Value("${kakao.token.uri}")
    private String tokenUri;

    @Value("${kakao.userinfo.uri}")
    private String userInfoUri;

    private final RestTemplate restTemplate = new RestTemplate();

    // 1. 인가 코드로 토큰 발급
    public KakaoTokenResponse getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = UriComponentsBuilder.newInstance()
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("code", code)
                .build()
                .toString()
                .substring(1);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                tokenUri,
                HttpMethod.POST,
                request,
                KakaoTokenResponse.class
        );

        return response.getBody();
    }

    // 2. 토큰으로 사용자 정보 가져오기
    public KakaoUserInfo getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(
                userInfoUri,
                HttpMethod.GET,
                request,
                KakaoUserInfo.class
        );

        return response.getBody();
    }
}