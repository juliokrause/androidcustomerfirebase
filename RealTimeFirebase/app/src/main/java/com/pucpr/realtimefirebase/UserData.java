package com.pucpr.realtimefirebase;

public class UserData {
    String username,email,creation_date;

    public UserData() {
    }

    public UserData(String username, String email, String creation_date) {
        this.username = username;
        this.email = email;
        this.creation_date = creation_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
