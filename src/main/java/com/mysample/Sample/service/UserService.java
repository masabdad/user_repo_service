package com.mysample.Sample.service;

import com.mysample.Sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM user",
                new BeanPropertyRowMapper<>(User.class));
    }

    public void addUser(User user) {
        String sql = "INSERT INTO user (name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail());
    }
}

