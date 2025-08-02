package com.study.Spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_composition")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductComposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long combinationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ValidStatus isValid;

    public enum ValidStatus {
        VALID, INVALID
    }
}