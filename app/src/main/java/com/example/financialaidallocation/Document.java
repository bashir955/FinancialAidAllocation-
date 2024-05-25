package com.example.financialaidallocation;

public class Document
{
    private String name;
    private String info;
    private String degreeType;
    private int imageResId;
    private String aridNo;

    public Document(int imageResId, String name, String aridNo) {
        this.imageResId = imageResId;
        this.name = name;
        this.aridNo = aridNo;
    }

    public String getName() {
        return name;
    }
    public int getImageResId() {
        return imageResId;
    }

    public String getAridNo() {
        return aridNo;
    }
    public String getInfo() {
        return info;
    }
    public String getDegreeType() {
        return degreeType;
    }
}
