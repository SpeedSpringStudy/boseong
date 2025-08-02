package com.study.Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockResponse {
    private Long stockId;
    private Long productId;
    private Integer quantity;
}