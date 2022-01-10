package com.deslabs.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/*
 *Author: Desterio
 *Date: 12/20/2021
 *Year: 2021
 */
@Entity

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int parentId;
    private String email;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String phone_number;
    private String other_phone_number;
    private String occupation;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PARENT_ROLE",
            joinColumns = @JoinColumn(name ="PARENT_ID", referencedColumnName = "parentId"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "role_id"))
    private List<Role>roles;

    @OneToMany( mappedBy = "parent")
    private List<Student>students;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getOther_phone_number() {
        return other_phone_number;
    }

    public void setOther_phone_number(String other_phone_number) {
        this.other_phone_number = other_phone_number;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
