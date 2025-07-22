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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userService.findById(id);
        return new CustomUserDetails(user);
    }
}