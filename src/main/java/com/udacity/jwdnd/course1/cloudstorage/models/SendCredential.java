package com.udacity.jwdnd.course1.cloudstorage.models;

public class SendCredential {
    public Integer credentialId;
    private String url;
    private String username;
    private String encryptedPassword;
    private String password;
    private Integer userId;

    public SendCredential(Integer credentialId, String url, String username, String encryptedPassword, String password, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
