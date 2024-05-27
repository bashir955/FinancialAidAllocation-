package com.example.financialaidallocation;

public class Student {
    private String name;
    private String aridNumber;

    public Student(String name, String aridNumber) {
        this.name = name;
        this.aridNumber = aridNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAridNumber() {
        return aridNumber;
    }

    public void setAridNumber(String aridNumber) {
        this.aridNumber = aridNumber;
    }
}

