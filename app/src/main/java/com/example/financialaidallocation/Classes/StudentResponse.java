package com.example.financialaidallocation.Classes;

import com.google.gson.annotations.SerializedName;

public class StudentResponse  {
    @SerializedName("student_id")
    private int studentId;

    @SerializedName("name")
    private String name;

    @SerializedName("arid_no")
    private String aridNo;

    // Other fields if any...

    // Getter and Setter methods
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAridNo() {
        return aridNo;
    }

    public void setAridNo(String aridNo) {
        this.aridNo = aridNo;
    }
//    @SerializedName("name")
//    private String name;
//
//    @SerializedName("aridNo")
//    private String aridNo;
//
//    // Getters and Setters
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getAridNo() {
//        return aridNo;
//    }
//    public void setAridNo(String aridNo) {
//        this.aridNo = aridNo;
//    }
}