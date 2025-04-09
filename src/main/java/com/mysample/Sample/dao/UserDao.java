package com.mysample.Sample.dao;

import com.mysample.Sample.model.User;

import java.util.List;

public interface UserDao {
    boolean userExists(String email);
    void saveUser(User user);

    void updateUser(User user);

    void delete(String email);
    List<User> getAllUsers();
   User userExistsById(Long id);

    User getUserById(Long id);
}