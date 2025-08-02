package com.study.Spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOptionValueRequest {
    private Long groupId;
    private String value;
}