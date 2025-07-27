package com.study.Spring.service;

//import com.study.Spring.dao.WishlistDao;
import com.study.Spring.dto.WishlistResponseDto;
import com.study.Spring.entity.User;
import com.study.Spring.entity.Product;
import com.study.Spring.entity.Wishlist;
import com.study.Spring.repository.ProductRepository;
import com.study.Spring.repository.UserRepository;
import com.study.Spring.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

//    private final WishlistDao wishlistDao;

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WishlistResponseDto addToWishlist(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        Product product = productRepository.findById(productId).orElseThrow(()->new IllegalArgumentException("상품을 찾을 수 없습니다."));
        Wishlist wishlist = Wishlist.builder()
                .user(user)
                .product(product)
                .build();
        Wishlist saved = wishlistRepository.save(wishlist);
        return new WishlistResponseDto(saved.getId(), product.getId(), product.getName(), product.getPrice());
    }

//    public List<WishlistResponseDto> getWishlist(Long userId) {
//        List<WishlistResponseDto> responseDto = userRepository.findAllById(userId).stream()
//                .map(w -> new WishlistResponseDto(
//                        w.getId(),
//                        w.getProduct().getId(),
//                        w.getProduct().getName().toString(),
//                        w.getProduct().getPrice()
//                )).tolist();
//        return responseDto;
//    }

    public Page<WishlistResponseDto> getWishlist(Long userId, Pageable pageable) {
        return wishlistRepository.findByUserId(userId, pageable)
                .map(w -> new WishlistResponseDto(
                        w.getId(),
                        w.getProduct().getId(),
                        w.getProduct().getName(),
                        w.getProduct().getPrice()
                ));
    }
    public void removeFromWishlist(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
    }
}