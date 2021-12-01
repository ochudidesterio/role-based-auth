package com.deslabs.school.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
 *Author: Desterio
 *Date: 11/19/2021
 *Year: 2021
 */

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})


@Entity
public class Department  {
    @Id
    private String depart_name;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
    private List<Teacher>teachers;

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
