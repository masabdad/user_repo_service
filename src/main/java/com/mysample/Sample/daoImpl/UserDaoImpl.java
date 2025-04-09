package com.mysample.Sample.daoImpl;

import com.mysample.Sample.dao.UserDao;
import com.mysample.Sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean userExists(String email) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO user (name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail());
    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user SET name = ? WHERE email = ?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail());
    }

    @Override
    public void delete(String email) {
        String sql = "DELETE FROM user WHERE email = ?";
        jdbcTemplate.update(sql, email);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
    @Override
    public User userExistsById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }
    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (EmptyResultDataAccessException e) {
            return null; // Return null if no user is found
        }
    }

}
