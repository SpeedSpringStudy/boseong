package com.study.Spring.controller;

import com.study.Spring.entity.Wishlist;
import com.study.Spring.security.JwtTokenProvider;
import com.study.Spring.service.WishlistService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/{productId}")
    public void add(@PathVariable Long productId, HttpServletRequest request) {
        String token = extractToken(request);
        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        wishlistService.addToWishlist(userId, productId);
    }

    @GetMapping
    public List<Wishlist> get(HttpServletRequest request) {
        String token = extractToken(request);
        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        return wishlistService.getWishlist(userId);
    }

    @DeleteMapping("/{wishlistId}")
    public void remove(@PathVariable Long wishlistId) {
        wishlistService.removeFromWishlist(wishlistId);
    }

    private String extractToken(HttpServletRequest request) {
        for (var cookie : request.getCookies()) {
            if (cookie.getName().equals("access_token")) {
                return cookie.getValue();
            }
        }
        throw new IllegalArgumentException("토큰이 없습니다.");
    }
}