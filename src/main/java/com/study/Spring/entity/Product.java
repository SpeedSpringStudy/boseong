package com.study.Spring.entity;

import com.study.Spring.vo.Name;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "composition_id")
    private ProductComposition composition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}