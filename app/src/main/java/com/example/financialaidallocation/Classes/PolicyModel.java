package com.example.financialaidallocation.Classes;

import java.io.Serializable;

public class PolicyModel implements Serializable {
    int id;
    String policy1;
    String session;
    String policyfor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPolicy1() {
        return policy1;
    }

    public void setPolicy1(String policy1) {
        this.policy1 = policy1;
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
}