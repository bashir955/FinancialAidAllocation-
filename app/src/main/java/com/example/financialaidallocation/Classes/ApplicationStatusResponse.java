package com.example.financialaidallocation.Classes;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.HTTP;

public class ApplicationStatusResponse {

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    private String applicationStatus;
}