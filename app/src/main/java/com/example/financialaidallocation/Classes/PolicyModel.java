package com.example.financialaidallocation.Classes;

public class PolicyModel {
    private String policyType;
    private String requiredCgpa;
    private String cgpaDescription;

    public PolicyModel(String policyType, String requiredCgpa, String cgpaDescription) {
        this.policyType = policyType;
        this.requiredCgpa = requiredCgpa;
        this.cgpaDescription = cgpaDescription;
    }

    public String getPolicyType() {
        return policyType;
    }

    public String getRequiredCgpa() {
        return requiredCgpa;
    }

    public String getCgpaDescription() {
        return cgpaDescription;
    }
}
