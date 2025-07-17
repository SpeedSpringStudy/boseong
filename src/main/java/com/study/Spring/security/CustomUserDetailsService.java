package com.study.Spring.security;

import com.study.Spring.entity.User;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // 예시용 - 실제는 DB 조회 로직 사용
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        User user = User.builder()
                .id(1L)
                .username("admin")
                .password("{noop}1234") // {noop}은 암호화 없이 저장
                .build();

        return new CustomUserDetails(user);
    }
}