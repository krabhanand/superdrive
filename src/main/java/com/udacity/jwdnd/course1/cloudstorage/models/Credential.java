package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credential {
    public Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userId;



    public Credential(Integer credentialId, String url, String username, String key,String password, Integer userId) {
        this.credentialId = credentialId;
        this.username = username;
        this.password = password;
        this.url = url;
        this.userId = userId;
        this.key=key;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
