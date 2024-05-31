package com.example.financialaidallocation.Classes;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private int id;
    private String userName;
    private String password;
    private int role;
    private int profileId;

    public LoginResponse(int id, String userName, String password, int role, int profileId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.profileId = profileId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}