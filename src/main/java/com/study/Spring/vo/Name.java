package com.study.Spring.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public record Name(@Column(name = "name_value") String value) {
    public Name {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("상품명은 필수입니다.");
        }
        if (value.length() > 15) {
            throw new IllegalArgumentException("상품명은 15자 이내여야 합니다.");
        }
        if (!value.matches("^[a-zA-Z0-9가-힣\\s\\(\\)\\[\\]\\+\\-&/_]*$")) {
            throw new IllegalArgumentException("상품 이름에는 한글, 영문, 숫자, ( ), [ ], +, -, &, /, _ 만 사용할 수 있습니다.");
        }
        if (value.contains("카카오")) {
            throw new IllegalArgumentException("\"카카오\"가 포함된 상품 이름은 MD 승인 후 사용 가능합니다.");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}