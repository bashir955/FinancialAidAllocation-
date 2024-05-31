package com.example.financialaidallocation.Classes;

public class StudentModel {
    private int studentId;
    private String name;
    private String arid_no;

    // Constructor, getters and setters
    public StudentModel(int studentId, String name, String arid_no) {
        this.studentId = studentId;
        this.name = name;
        this.arid_no = arid_no;
    }

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

    public String getArid_no() {
        return arid_no;
    }

    public void setArid_no(String arid_no) {
        this.arid_no = arid_no;
    }
}
