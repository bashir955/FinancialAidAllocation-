package com.example.financialaidallocation;
public class MeritbaseStudentModel {
    private String name;
    private String aridNo;

    public MeritbaseStudentModel(String name, String aridNo) {
        this.name = name;
        this.aridNo = aridNo;
    }

    public String getName() {
        return name;
    }

    public String getAridNo() {
        return aridNo;
    }
}
