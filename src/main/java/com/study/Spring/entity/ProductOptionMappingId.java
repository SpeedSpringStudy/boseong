package com.study.Spring.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionMappingId implements Serializable {
    private Long productComposition;
    private Long optionValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductOptionMappingId that)) return false;
        return Objects.equals(productComposition, that.productComposition) &&
                Objects.equals(optionValue, that.optionValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productComposition, optionValue);
    }
}