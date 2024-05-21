package com.example.financialaidallocation;

public class Document
{
    private String name;
    private String info;
    private String degreeType;

    public Document(String name, String info, String degreeType) {
        this.name = name;
        this.info = info;
        this.degreeType = degreeType;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
    public String getDegreeType() {
        return degreeType;
    }
}
