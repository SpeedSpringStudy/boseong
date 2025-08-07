package com.study.Spring.security;

import com.study.Spring.entity.User;
import com.study.Spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    // Spring Security에서 username 대신 email로 인증
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        return new CustomUserDetails(user);
    }

    // JWT 토큰 검증 시 ID 기반으로 사용자 로드
    public UserDetails loadUserById(Long id) {
        User user = userService.findById(id);
        return new CustomUserDetails(user);
    }
}