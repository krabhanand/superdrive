package com.udacity.jwdnd.course1.cloudstorage.models;

public class Param {
    private Boolean logout;
    private Boolean error;

    public Param(Boolean logout, Boolean error) {
        this.logout = logout;
        this.error = error;
    }

    public Boolean getLogout() {
        return logout;
    }

    public void setLogout(Boolean logout) {
        this.logout = logout;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
