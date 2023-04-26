package com.example.Match_Service;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user = new User(
                rs.getString("userId"),
                rs.getString("firstName"),
                rs.getString("secondName"),
                rs.getString("lastName"),
                rs.getInt("age"),
                rs.getString("userDescription"),
                rs.getString("location"),
                rs.getString("hobby"));
        System.out.println(user);
        return user;
    }
}
