package com.study.Spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "option_groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id", nullable = false)
    private Product product;

    @Column(nullable = false, length = 50)
    private String name;
}