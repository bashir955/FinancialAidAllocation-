package com.example.financialaidallocation.Classes;

public class PolicyModel {


    private String description;
    private int strength;
    private String session;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getPolicyfor() {
        return policyfor;
    }

    public void setPolicyfor(String policyfor) {
        this.policyfor = policyfor;
    }

    public String getPolicy1() {
        return policy1;
    }

    public void setPolicy1(String policy1) {
        this.policy1 = policy1;
    }

    public double getVal1() {
        return val1;
    }

    public void setVal1(double val1) {
        this.val1 = val1;
    }

    private String policyfor;
    private String policy1;
    private double val1;
}