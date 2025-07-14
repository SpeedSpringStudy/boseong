package com.study.Spring.dto;

import jakarta.validation.constraints.*;

public record ProductRequestDto(
        @NotBlank(message = "상품 이름은 필수입니다.")
        @Size(max = 15, message = "상품 이름은 15자 이하로 입력해야 합니다.")
        @Pattern(
                regexp = "^[a-zA-Z0-9가-힣\\s\\(\\)\\[\\]\\+\\-&/_]*$",
                message = "상품 이름에는 한글, 영문, 숫자, ( ), [ ], +, -, &, /, _ 만 사용할 수 있습니다."
        )
        String name,

        @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
        int price
) {}