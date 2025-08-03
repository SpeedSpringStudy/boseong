package com.study.Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockResponse {
    private Long stockId;
    private Long compositionId;
    private Integer quantity;
}