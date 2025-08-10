package com.study.Spring.controller;

import com.study.Spring.dto.OrderRequest;
import com.study.Spring.dto.OrderResponse;
import com.study.Spring.security.JwtTokenProvider;
import com.study.Spring.service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid OrderRequest request, HttpServletRequest http) {
        String token = resolveToken(http);
        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        OrderResponse res = orderService.placeOrder(userId, request);
        return ResponseEntity.status(201).body(res);
    }

    private String resolveToken(HttpServletRequest req) {
        String bearer = req.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) return bearer.substring(7);
        if (req.getCookies() != null) {
            for (Cookie c : req.getCookies()) if ("access_token".equals(c.getName())) return c.getValue();
        }
        throw new IllegalArgumentException("토큰이 없습니다.");
    }
}