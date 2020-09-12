package com.udacity.jwdnd.course1.cloudstorage.models;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String salt;
    private String password;

    public User(Object o,String username, String salt,String password,String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.salt = salt;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }
}
