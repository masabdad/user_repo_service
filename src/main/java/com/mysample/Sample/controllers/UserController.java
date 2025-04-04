package com.mysample.Sample.controllers;

import com.mysample.Sample.constants.ApiConstants;
import com.mysample.Sample.model.User;
import com.mysample.Sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.USERS_ENDPOINT)
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/get")
    @PreAuthorize(ApiConstants.ROLE_ADMIN)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PostMapping("/add")
    @PreAuthorize(ApiConstants.ROLE_ADMIN)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        return userService.logoutUser(request, response);
    }

    @PutMapping("/update")
    @PreAuthorize(ApiConstants.ROLE_ADMIN)
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{email}")
    @PreAuthorize(ApiConstants.ROLE_ADMIN)
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        return userService.deleteUser(email);
    }

}
