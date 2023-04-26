package com.example.Match_Service;

public class Match {
    private String firstUserId;

    private String secondUserId;

    private String matchStatus;

    public Match(String firstUserId, String secondUserId, String matchStatus) {
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
        this.matchStatus = matchStatus;
    }

    public String getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(String firstUserId) {
        this.firstUserId = firstUserId;
    }

    public String getSecondUserId() {
        return secondUserId;
    }

    public void setSecondUserId(String secondUserId) {
        this.secondUserId = secondUserId;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }
}