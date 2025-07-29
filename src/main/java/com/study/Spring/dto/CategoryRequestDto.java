package com.study.Spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDto(
        @NotBlank(message = "카테고리 이름은 필수입니다.")
        @Size(max = 50, message = "카테고리 이름은 50자 이하로 입력해야 합니다.")
        String name,

        @Size(max = 255, message = "카테고리 설명은 255자 이하로 입력해야 합니다.")
        String description
) {}