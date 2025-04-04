package com.mysample.Sample.service;

import com.mysample.Sample.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    ResponseEntity<String> registerUser(User user);

    ResponseEntity<String> logoutUser(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity<String> updateUser(User user);

    ResponseEntity<String> deleteUser(String email);
}
