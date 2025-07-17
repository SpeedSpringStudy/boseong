package com.study.Spring.dao;

import com.study.Spring.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> User.builder()
            .id(rs.getLong("id"))
            .username(rs.getString("username"))
            .password(rs.getString("password"))
            .build();

    public void save(User user) {
        jdbcTemplate.update(
                "INSERT INTO users (username, password) VALUES (?, ?)",
                user.getUsername(), user.getPassword()
        );
    }

    public Optional<User> findByUsername(String username) {
        List<User> results = jdbcTemplate.query(
                "SELECT * FROM users WHERE username = ?",
                userRowMapper,
                username
        );
        return results.stream().findFirst();
    }

    public boolean existsByUsername(String username) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users WHERE username = ?",
                Integer.class,
                username
        ) > 0;
    }
}