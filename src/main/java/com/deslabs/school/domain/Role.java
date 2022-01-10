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

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

@Entity

public class Role {
    @Id
    private int role_id;
    private String role_name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")

    private List<Teacher> teacher;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")

    private List<Parent> parent;


    public Role(String name) {
        this.role_name=name;
    }

    public Role() {

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



    public List<Teacher> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }

    public List<Parent> getParent() {
        return parent;
    }

    public void setParent(List<Parent> parent) {
        this.parent = parent;
    }
}
