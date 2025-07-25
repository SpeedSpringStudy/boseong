package com.study.Spring.dto;

import com.study.Spring.vo.Name;

public record WishlistResponseDto(
        Long id,
        Long productId,
        Name productName,
        int productPrice
) {}