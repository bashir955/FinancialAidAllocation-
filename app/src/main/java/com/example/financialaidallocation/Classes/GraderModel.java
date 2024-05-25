package com.example.financialaidallocation.Classes;

public class GraderModel {
    private String name;
    private String aridNo;
    private String profileImagePath;

    public GraderModel(String name, String aridNo, String profileImagePath) {
        this.name = name;
        this.aridNo = aridNo;
        this.profileImagePath = profileImagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAridNo() {
        return aridNo;
    }

    public void setAridNo(String aridNo) {
        this.aridNo = aridNo;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }}
