package com.example.Match_Service;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchRowMapper implements RowMapper<Match> {

    @Override
    public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
        Match match = new Match(
                rs.getString("firstUserId"),
                rs.getString("secondUserId"),
                rs.getString("matchStatus")
        );
        return match;
    }
}
