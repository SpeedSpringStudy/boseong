package com.study.Spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "kakao_id", unique = true)
    private Long kakaoId;

    @Column(name = "kakao_access_token", length = 2048)
    private String kakaoAccessToken;

    @Column(name = "kakao_refresh_token", length = 2048)
    private String kakaoRefreshToken;

    @Column(name = "kakao_token_expires_at")
    private LocalDateTime kakaoTokenExpiresAt;
}