package com.example.financialaidallocation.Classes;

import java.io.Serializable;

public class ScholorShipPolicy implements Serializable {
    PolicyModel p;
    CriteriaModel c;

    public PolicyModel getP() {
        return p;
    }

    public void setP(PolicyModel p) {
        this.p = p;
    }

    public CriteriaModel getC() {
        return c;
    }

    public void setC(CriteriaModel c) {
        this.c = c;
    }
}
