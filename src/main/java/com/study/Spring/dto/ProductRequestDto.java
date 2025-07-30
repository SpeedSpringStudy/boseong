package com.study.Spring.dto;

public record ProductRequestDto(
        String name,
        int price,
        Long categoryId
) {}