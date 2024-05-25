package com.example.financialaidallocation.Classes;

public class CommitteeMemberModel {
    private String name;
    private String profileImagePath;

    public CommitteeMemberModel(String name, String profileImagePath) {
        this.name = name;
        this.profileImagePath = profileImagePath;
    }
    public String getName() {
        return name;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}
