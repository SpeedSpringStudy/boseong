package com.study.Spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOptionGroupRequest {
    private Long productId;
    private String name;
}