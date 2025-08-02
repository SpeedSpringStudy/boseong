package com.study.Spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OptionValueResponse {
    private Long id;
    private String value;
    private Long groupId;
}