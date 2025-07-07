package com.study.Spring.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private int price;
}