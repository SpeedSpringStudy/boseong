package com.study.Spring.service;

import com.study.Spring.entity.User;
import com.study.Spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.study.Spring.entity.Role;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 일반 사용자 등록
    @Transactional
    public void register(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        User user = User.builder()
                .email(email)
                .password("{noop}" + password) // 패스워드 인코딩 필요 시 변경
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    // 관리자 등록
    @Transactional
    public void registerAdmin(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        User admin = User.builder()
                .email(email)
                .password("{noop}" + password)
                .role(Role.ADMIN)
                .build();

        userRepository.save(admin);
    }

    // 이메일로 사용자 조회
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    // Refresh Token 업데이트
    @Transactional
    public void updateRefreshToken(String email, String refreshToken) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    }

    // ID로 사용자 조회
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    // 카카오 사용자 찾거나 신규 생성
    @Transactional
    public User findOrCreateByKakaoId(Long kakaoId, String email) {
        return userRepository.findByEmail(email)
                .map(existingUser -> {
                    existingUser.setKakaoId(kakaoId);
                    return userRepository.save(existingUser);
                })
                .orElseGet(() -> userRepository.save(User.builder()
                        .email(email)
                        .password("{noop}" + UUID.randomUUID()) // 임시 비밀번호
                        .kakaoId(kakaoId)
                        .role(Role.USER)
                        .build()));
    }
}