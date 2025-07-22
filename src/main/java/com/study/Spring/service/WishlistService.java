package com.study.Spring.service;

import com.study.Spring.dao.WishlistDao;
import com.study.Spring.entity.Wishlist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistDao wishlistDao;

    public void addToWishlist(Long userId, Long productId) {
        Wishlist wishlist = Wishlist.builder()
                .userId(userId)
                .productId(productId)
                .build();
        wishlistDao.save(wishlist);
    }

    public List<Wishlist> getWishlist(Long userId) {
        return wishlistDao.findByUserId(userId);
    }

    public void removeFromWishlist(Long wishlistId) {
        wishlistDao.delete(wishlistId);
    }
}