package com.study.Spring.service;

import com.study.Spring.entity.Order;
import com.study.Spring.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class KakaoMessageService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String SEND_URL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

    public void sendOrderMemo(User user, Order order) {
        if (user.getKakaoAccessToken() == null) return;

        String text = "주문 완료\n옵션ID: " + order.getComposition().getId() +
                "\n수량: " + order.getQuantity() +
                "\n시간: " + order.getOrderDateTime() +
                (order.getMessage() != null && !order.getMessage().isBlank() ? "\n메시지: " + order.getMessage() : "");

        String templateJson = "{\"object_type\":\"text\",\"text\":\"" +
                escapeJson(text) +
                "\",\"link\":{\"web_url\":\"http://localhost:8080\",\"mobile_web_url\":\"http://localhost:8080\"}}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(user.getKakaoAccessToken());

        String body = "template_object=" + URLEncoder.encode(templateJson, StandardCharsets.UTF_8);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        restTemplate.exchange(SEND_URL, HttpMethod.POST, entity, String.class);
    }

    private String escapeJson(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }
}