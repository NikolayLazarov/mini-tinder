package com.example.Match_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

@Repository
public class MatchingService {

    private StringKafkaProducer kafkaProducer;

    @Autowired
    public MatchingService(StringKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //    @Autowired
//    private KafkaTemplate< String,Match> kafkaTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAllMatches(String userId) {
        String sql = String.format("SELECT u.*, h.hobby, l.location FROM minitinder.usersData\n" +
                "join hobbies h on h.hobbyId = usersdata.hobbiesId\n" +
                "join locations l on l.locationId = usersdata.locationId\n" +
                "join users u on u.userId = usersdata.userId\n" +
                "and u.userId != '%s';",userId);
        List <User> queryUsers = jdbcTemplate.query(
                sql, new UserRowMapper());
        System.out.println(queryUsers);


//        queryUsers.forEach(user -> {kafkaTemplate.send("matches",user);});
        

        return queryUsers;
    }

    public List<User> getAllMatchesByLocation(String userlocation, String userId) {
        String sql = String.format("SELECT u.*, h.hobby, l.location FROM minitinder.usersData\n" +
                "join hobbies h on h.hobbyId = usersdata.hobbiesId\n" +
                "join locations l on l.locationId = usersdata.locationId\n" +
                "join users u on u.userId = usersdata.userId\n" +
                "where l.location = '%s'\n" +
                "and u.userId != '%s';",userlocation,userId);
        List <User> queryUsers = jdbcTemplate.query(
                sql, new UserRowMapper());
        System.out.println(queryUsers);
        return queryUsers;
    }

    public List<User> getAllMatchesByHobby(String userHobby,String  userId) {
        String sql = String.format("SELECT u.*, h.hobby, l.location FROM minitinder.usersData\n" +
                "join hobbies h on h.hobbyId = usersdata.hobbiesId\n" +
                "join locations l on l.locationId = usersdata.locationId\n" +
                "join users u on u.userId = usersdata.userId\n" +
                "where h.hobby = '%s'" +
                "and u.userId != '%s'", userHobby,userId);

        List <User> queryUsers = jdbcTemplate.query(
                sql, new UserRowMapper());
        return queryUsers;
    }

    public List<User> getAllSuitableMatches(String userId, String userLocation, String userHobby) {

        String sql = String.format("SELECT u.*, h.hobby, l.location FROM minitinder.usersData\n" +
                "join hobbies h on h.hobbyId = usersdata.hobbiesId\n" +
                "join locations l on l.locationId = usersdata.locationId\n" +
                "join users u on u.userId = usersdata.userId\n" +
                "where h.hobby = '%s' and \n" +
                "l.location = '%s' and\n" +
                "u.userId != '%s';",userHobby, userLocation, userId);

        List <User> queryUsers = jdbcTemplate.query(
                sql, new UserRowMapper());
        return queryUsers;
    }

    public User getMatch(String id){
         String sql = String.format("SELECT u.*, h.hobby, l.location FROM minitinder.usersData\n" +
                 "join hobbies h on h.hobbyId = usersdata.hobbiesId\n" +
                 "join locations l on l.locationId = usersdata.locationId\n" +
                 "join users u on u.userId = usersdata.userId\n" +
                 "where usersdata.userId = '%s';",id);
         User user =  jdbcTemplate.queryForObject(sql,new UserRowMapper());
        assert user != null;
        return user;
    }

    public void addMatch(String id,Map<String,String> match) {

        jdbcTemplate.update("INSERT INTO minitinder.matches(firstUserId, secondUserId, matchStatus)  VALUE(?,?,?);",
                id, match.get("id"), match.get("status"));
        Match newMatch = new Match (id,match.get("id"),match.get("status"));
        String message= String.format("{ " +
                "id1: %s," +
                "id2: %s," +
                "}",id, match.get("id"));
    if (match.get("status").equals("approved")){
            kafkaProducer.sendMessage(message);

        }
    }

    public List<Match> getRequests(String id) {
        String sql = "SELECT * FROM minitinder.matches " +
                "where firstUserId = '" + id + "';";

        List<Match> matches = jdbcTemplate.query(sql,
                        new MatchRowMapper());
        return matches;
    }

    public Match getRequest(String currentUserId, String id) {
        String sql = "SELECT * FROM minitinder.matches " +
                "where firstUserId = '" + currentUserId + "' " +
                "and secondUserId = '" + id + "';";

        Match match = jdbcTemplate.queryForObject(
                sql,
                new MatchRowMapper());
        System.out.println(match);
        return match;
    }

    public void updateMatch(String currentUserId, Map<String, String> newStatusMatch) {
        String sql = "UPDATE minitinder.matches SET matchStatus = ? where firstUserId = ? and secondUserId = ?" ;

        jdbcTemplate.update(sql,newStatusMatch.get("status"), currentUserId, newStatusMatch.get("id"));
    }


}
