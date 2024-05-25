package com.example.financialaidallocation.Classes;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.GET;
import retrofit2.http.HTTP;

public class ApplicationStatusResponse {

    @SerializedName("applicationStatus")
    private String applicationStatus;

    // Getter and Setter methods
    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}