package com.study.Spring.service;

//import com.study.Spring.dao.UserDao;
import com.study.Spring.entity.User;
import com.study.Spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.study.Spring.entity.Role;

@Service
@RequiredArgsConstructor
public class UserService {

//    private final UserDao userDao;
    private final UserRepository userRepository;

    @Transactional
    public void register(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        User user = User.builder()
                .username(username)
                .password("{noop}" + password)
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    @Transactional
    public void registerAdmin(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        User admin = User.builder()
                .username(username)
                .password("{noop}" + password)
                .role(Role.ADMIN)
                .build();

        userRepository.save(admin);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    public void updateRefreshToken(String username, String refreshToken) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    }

    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }
}