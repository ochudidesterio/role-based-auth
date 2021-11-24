package com.deslabs.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.util.List;

/*
 *Author: Desterio
 *Date: 11/19/2021
 *Year: 2021
 */


@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int role_id;
    private String role_name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")

    private List<Student> student;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")

    private List<Teacher> teacher;

    public Role(String role) {
    }

    public Role() {
    }

    public Role(int role_id, String role_name, List<Student> student, List<Teacher> teacher) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.student = student;
        this.teacher = teacher;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public List<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }
}
