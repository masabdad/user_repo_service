package com.mysample.Sample.serviceImpl;


import com.mysample.Sample.constants.ApiConstants;
import com.mysample.Sample.daoImpl.UserDaoImpl;
import com.mysample.Sample.exceptionHandling.UserAlreadyExistsException;
import com.mysample.Sample.model.User;
import com.mysample.Sample.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserDaoImpl userDao;

    @Override
    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    @Override
    public ResponseEntity<String> registerUser(User user) {
        try {
            if (userDao.userExists(user.getEmail())) {
                throw new UserAlreadyExistsException(ApiConstants.USER_ALREADY_EXISTS);
            }
            userDao.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiConstants.USER_SIGNUP_SUCCESS);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error signing up user: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> logoutUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().invalidate();
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseEntity.ok(ApiConstants.USER_LOGOUT_SUCCESS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiConstants.USER_LOGOUT_FAIL + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> updateUser(User user) {
        if (!userDao.userExists(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiConstants.USER_NOT_FOUND + user.getEmail());
        }
        userDao.updateUser(user);
        return ResponseEntity.ok(ApiConstants.USER_UPDATED);
    }

    @Override
    public ResponseEntity<String> deleteUser(String email) {
        if (!userDao.userExists(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiConstants.USER_NOT_FOUND + email);
        }
        userDao.delete(email);
        return ResponseEntity.ok(ApiConstants.USER_DELETED_SUCCESSFULLY);
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        try {
            User user = userDao.getUserById(id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

