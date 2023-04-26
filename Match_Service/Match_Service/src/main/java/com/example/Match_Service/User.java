package com.example.Match_Service;


public class User {
    private String id;
    private String firstname;
    private String secondname;

    private String lastname;

    private int age;

    private String userDescription;
    private String location;

    private String hobby;

    public User(String id, String firstname, String secondname, String lastname, int age, String userDescription, String location, String hobby) {
        this.id = id;
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.age = age;
        this.userDescription = userDescription;
        this.location = location;
        this.hobby = hobby;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
