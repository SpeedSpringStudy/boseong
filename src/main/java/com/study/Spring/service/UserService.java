package com.study.Spring.service;

import com.study.Spring.dao.UserDao;
import com.study.Spring.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public void register(String username, String password) {
        if (userDao.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        User user = User.builder()
                .username(username)
                .password("{noop}" + password) // 나중에 BCrypt로 변경
                .build();

        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    public void updateRefreshToken(String username, String refreshToken) {
        userDao.updateRefreshToken(username, refreshToken); // DAO 메서드 호출
    }
}