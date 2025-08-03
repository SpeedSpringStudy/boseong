package com.study.Spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockRequest {
    private Long productId;
    private int quantity;
}