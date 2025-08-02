package com.study.Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OptionGroupResponse {
    private Long id;
    private String name;
    private Long productId;
}