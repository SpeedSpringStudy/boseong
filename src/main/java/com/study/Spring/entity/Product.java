package com.study.Spring.entity;

import com.study.Spring.vo.Name;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private Name name;
    private int price;
}