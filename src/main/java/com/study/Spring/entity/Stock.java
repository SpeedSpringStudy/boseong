package com.study.Spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "combination_id", nullable = false, unique = true)
    private ProductComposition productComposition;

    @Column(nullable = false)
    private int quantity;

    @Version
    private Long version;
}