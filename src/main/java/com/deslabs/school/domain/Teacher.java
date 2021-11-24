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

@Entity
public class Teacher {
    @Id
    private int teacher_Id;
    private String first_name;
    private String last_name;
    private int age;
    private String email;
    private String salary;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToMany
    @JoinTable(name = "TEACHER_ROLE",
            joinColumns = @JoinColumn(name ="TEACHER_ID", referencedColumnName = "teacher_Id"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "role_id"))
    private List<Role> roles;

   // @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_NAME", referencedColumnName = "depart_name")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @JsonIgnore
    @OneToOne(mappedBy = "teacher")
    private StudyYear studyYear;

    public int getTeacher_Id() {
        return teacher_Id;
    }

    public void setTeacher_Id(int teacher_Id) {
        this.teacher_Id = teacher_Id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(StudyYear studyYear) {
        this.studyYear = studyYear;
    }
}
