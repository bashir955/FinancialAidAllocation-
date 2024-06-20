package com.example.financialaidallocation.Classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApplicationModel {

    @SerializedName("arid_no")
    private String aridNo;
    @SerializedName("name")
    private String name;
    @SerializedName("student_id")
    private int studentId;
    @SerializedName("father_name")
    private String fatherName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("degree")
    private String degree;
    @SerializedName("cgpa")
    private double cgpa;
    @SerializedName("semester")
    private int semester;
    @SerializedName("section")
    private String section;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("applicationDate")
    private String applicationDate;
    @SerializedName("reason")
    private String reason;
    @SerializedName("requiredAmount")
    private double requiredAmount;
    @SerializedName("EvidenceDocuments")
    private List<EvidenceDocumentModel> evidenceDocuments;

    public List<EvidenceDocumentModel> getEvidenceDocuments() {
        return evidenceDocuments;
    }

    public void setEvidenceDocuments(List<EvidenceDocumentModel> evidenceDocuments) {
        this.evidenceDocuments = evidenceDocuments;
    }

    @SerializedName("applicationID")
    private int applicationId;
    @SerializedName("session")
    private String session;
    @SerializedName("father_status")
    private String fatherStatus;
    @SerializedName("jobtitle")
    private String jobTitle;
    @SerializedName("salary")
    private double salary;
    @SerializedName("guardian_contact")
    private String guardianContact;
    @SerializedName("house")
    private String house;
    @SerializedName("guardian_name")
    private String guardianName;

    // Getters and Setters

    public String getAridNo() {
        return aridNo;
    }

    public void setAridNo(String aridNo) {
        this.aridNo = aridNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

//    public String getProfileImage() {
//        return profileImage;
//    }
    public String getProfileImageUrl() {
        return Config.IMAGE_BASE_URL + profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(double requiredAmount) {
        this.requiredAmount = requiredAmount;
    }



    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getFatherStatus() {
        return fatherStatus;
    }

    public void setFatherStatus(String fatherStatus) {
        this.fatherStatus = fatherStatus;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getGuardianContact() {
        return guardianContact;
    }

    public void setGuardianContact(String guardianContact) {
        this.guardianContact = guardianContact;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }
}
