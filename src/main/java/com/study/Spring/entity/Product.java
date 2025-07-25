package com.study.Spring.entity;

import com.study.Spring.vo.Name;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    private int price;
}