package com.study.Spring.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wishlist {
    private Long id;
    private Long userId;
    private Long productId;
}