//package com.study.Spring.dao;
//
//import com.study.Spring.entity.Wishlist;
//import lombok.RequiredArgsConstructor;
//import org.springframework.jdbc.core.*;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//@RequiredArgsConstructor
//public class WishlistDao {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    private final RowMapper<Wishlist> rowMapper = (rs, rowNum) -> Wishlist.builder()
//            .id(rs.getLong("id"))
//            .userId(rs.getLong("user_id"))
//            .productId(rs.getLong("product_id"))
//            .build();
//
//    public void save(Wishlist wishlist) {
//        jdbcTemplate.update(
//                "INSERT INTO wishlist (user_id, product_id) VALUES (?, ?)",
//                wishlist.getUserId(), wishlist.getProductId()
//        );
//    }
//
//    public List<Wishlist> findByUserId(Long userId) {
//        return jdbcTemplate.query(
//                "SELECT * FROM wishlist WHERE user_id = ?",
//                rowMapper, userId
//        );
//    }
//
//    public void delete(Long id) {
//        jdbcTemplate.update("DELETE FROM wishlist WHERE id = ?", id);
//    }
//}