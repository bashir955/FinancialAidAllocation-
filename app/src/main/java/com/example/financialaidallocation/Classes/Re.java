package com.example.financialaidallocation.Classes;

import java.io.Serializable;
import java.util.List;

public class Re implements Serializable {

        private int id;
        private String arid_no;
        private String name;
        private int student_id;
        private String father_name;
        private String gender;
        private String degree;
        private String  cgpa;
        private int semester;
        private String section;
        private String profile_image;
        private String applicationDate;
        private String reason;
        private String requiredAmount;
        private List<EvidenceDocumentModel> evidenceDocuments;
        private int applicationID;
        private String session;
        private String father_status;
        private String jobtitle;
        private String salary;
        private String guardian_contact;
        private String house;
        private String guardian_name;
        private List<SuggestionModel> suggestion;

        // Getters and Setters
        public int getId() {
        return id;
    }

        public void setId(int id) {
        this.id = id;
    }

        public String getArid_no() {
        return arid_no;
    }

        public void setArid_no(String arid_no) {
        this.arid_no = arid_no;
    }

        public String getName() {
        return name;
    }

        public void setName(String name) {
        this.name = name;
    }

        public int getStudent_id() {
        return student_id;
    }

        public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

        public String getFather_name() {
        return father_name;
    }

        public void setFather_name(String father_name) {
        this.father_name = father_name;
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


    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
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

        public String getProfile_image() {
        return profile_image;
    }

        public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
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

        public String getRequiredAmount() {
        return requiredAmount;
    }

        public void setRequiredAmount(String requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

        public List<EvidenceDocumentModel> getEvidenceDocuments() {
        return evidenceDocuments;
    }

        public void setEvidenceDocuments(List<EvidenceDocumentModel> evidenceDocuments) {
        this.evidenceDocuments = evidenceDocuments;
    }

        public int getApplicationID() {
        return applicationID;
    }

        public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

        public String getSession() {
        return session;
    }

        public void setSession(String session) {
        this.session = session;
    }

        public String getFather_status() {
        return father_status;
    }

        public void setFather_status(String father_status) {
        this.father_status = father_status;
    }

        public String getJobtitle() {
        return jobtitle;
    }

        public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

        public String getSalary() {
        return salary;
    }

        public void setSalary(String salary) {
        this.salary = salary;
    }

        public String getGuardian_contact() {
        return guardian_contact;
    }

        public void setGuardian_contact(String guardian_contact) {
        this.guardian_contact = guardian_contact;
    }

        public String getHouse() {
        return house;
    }

        public void setHouse(String house) {
        this.house = house;
    }

        public String getGuardian_name() {
        return guardian_name;
    }

        public void setGuardian_name(String guardian_name) {
        this.guardian_name = guardian_name;
    }

        public List<SuggestionModel> getSuggestion() {
        return suggestion;
    }

        public void setSuggestion(List<SuggestionModel> suggestion) {
        this.suggestion = suggestion;
    }
    }

