package com.example.Match_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MatchController {

    @Autowired
    private MatchingService matchingService;

    @RequestMapping("/users")
    public List<User> getAllMatches() {
        String userId = "U1";
        return matchingService.getAllMatches(userId);
    }

    @RequestMapping("/users/locations")
    public List<User> getMatchesByLocation(){
        String userLocation = "Plovdiv";
        String userId = "U1";
        return matchingService.getAllMatchesByLocation(userLocation,userId);
    }
    @RequestMapping("/users/hobbies")
    public List<User> getMatchesByHobby(){
        String userHobby = "skiing";
        String userId = "U1";
        return matchingService.getAllMatchesByHobby(userHobby,userId);
    }
    @RequestMapping("/users/matches")
    public List<User> getSutableMatches(){
        String userId = "U1";
        String userLocation = "Plovdiv";
        String userHobby = "skiing";
        return matchingService.getAllSuitableMatches(userId,userLocation,userHobby);
    }


    @RequestMapping("/users/{id}")
    public User getMatch(@PathVariable String id){
        return matchingService.getMatch(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/requests/{id}")
    public void rejectUser(String id, @RequestBody Map<String, String> statusMatch){
        //String id is the id of the current user
        id = "U1";
        matchingService.addMatch(id, statusMatch);
    }

    @RequestMapping("/requests")
    public List<Match> getRequests(String id){
        id = "U1";
       return matchingService.getRequests(id);
    }

    @RequestMapping("/requests/{id}")
    public Match getRequest(String currentUserId, @PathVariable String id){
        currentUserId = "U1";
        return matchingService.getRequest(currentUserId,id);
    }

    @RequestMapping (method = RequestMethod.PUT, value = "/requests/{id}")
    public void updateRequestStatus(String currentUserId, @RequestBody Map <String, String> newStatusMatch){
        currentUserId = "U1";
        matchingService.updateMatch(currentUserId, newStatusMatch);
    }


}
