package com.study.Spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_option_mapping")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(ProductOptionMappingId.class)
public class ProductOptionMapping {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combination_id")
    private ProductComposition productComposition;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "value_id")
    private OptionValue optionValue;
}