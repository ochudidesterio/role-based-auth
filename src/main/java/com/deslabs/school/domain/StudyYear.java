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
public class StudyYear {
    @Id
    private int year;

    @OneToMany(mappedBy = "studyYear")
    List<Student> students;

    @OneToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "teacher_id")
    private Teacher teacher;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
