package com.study.Spring.entity;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
}