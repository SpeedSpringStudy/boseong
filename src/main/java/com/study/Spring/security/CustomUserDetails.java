package com.study.Spring.security;

import com.study.Spring.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.*;

import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) { this.user = user; }

    @Override public String getUsername() { return user.getUsername(); }
    @Override public String getPassword() { return user.getPassword(); }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
    @Override public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    public User getUser() { return user; }
}