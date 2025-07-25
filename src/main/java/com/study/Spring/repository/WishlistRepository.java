package com.study.Spring.repository;

import com.study.Spring.entity.Wishlist;
import com.study.Spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUserId(Long userId);
}
