package com.example.financialaidallocation.Classes;


public class StudentResponse  {
    private int student_id;
    private String name;
    private String arid_no;
    private String degree;
    private int semester;
    private double cgpa;
    private String profileId;
    // Add other fields if necessary

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setArid_no(String arid_no) {
        this.arid_no = arid_no;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileId() {
        return profileId;
    }
    // Getters and setters
    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAridNo() {
        return arid_no;
    }

    public void setAridNo(String arid_no) {
        this.arid_no = arid_no;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }}