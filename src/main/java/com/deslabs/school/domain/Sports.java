package com.deslabs.school.domain;

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
public class Sports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sport_id;
    private String sport_name;

    @ManyToMany
    @JoinTable(name = "STUDENT_SPORT",
            joinColumns = @JoinColumn(name = "SPORT", referencedColumnName = "sport_id"),
            inverseJoinColumns = @JoinColumn(name = "STUD_REG", referencedColumnName = "regno"))
    private List<Student> students;

    public int getSport_id() {
        return sport_id;
    }

    public void setSport_id(int sport_id) {
        this.sport_id = sport_id;
    }

    public String getSport_name() {
        return sport_name;
    }

    public void setSport_name(String sport_name) {
        this.sport_name = sport_name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
