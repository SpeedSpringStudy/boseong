package com.study.Spring.repository;

import com.study.Spring.entity.Wishlist;
import com.study.Spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Page<Wishlist> findByUserId(Long userId, Pageable pageable);
}