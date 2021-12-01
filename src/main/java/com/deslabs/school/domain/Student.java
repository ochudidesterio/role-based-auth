package com.deslabs.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/*
 *Author: Desterio
 *Date: 11/19/2021
 *Year: 2021
 */


@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int regno;
    private String first_name;
    private String last_name;
    private String email;
    private String fee_paid;
    private String fee;
    private int age;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToMany
    @JoinTable(name = "STUDENT_ROLE",
            joinColumns = @JoinColumn(name ="STUDENT_ID", referencedColumnName = "regno"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "role_id"))
    private List<Role>roles;

    @JsonIgnore
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_YEAR", referencedColumnName = "year")
    private StudyYear studyYear;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private List<Course>courses;

    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private List<Sports>sports;

    public int getRegno() {
        return regno;
    }

    public void setRegno(int regno) {
        this.regno = regno;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFee_paid() {
        return fee_paid;
    }

    public void setFee_paid(String fee_paid) {
        this.fee_paid = fee_paid;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(StudyYear studyYear) {
        this.studyYear = studyYear;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Sports> getSports() {
        return sports;
    }

    public void setSports(List<Sports> sports) {
        this.sports = sports;
    }
}
