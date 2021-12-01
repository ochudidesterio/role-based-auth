package com.deslabs.school.domain;

/*
 *Author: Desterio
 *Date: 11/19/2021
 *Year: 2021
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unit;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "teacher_id")
    private Teacher teacher;

    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Book> books;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "COURSE_UNIT", referencedColumnName = "unit"),
            inverseJoinColumns = @JoinColumn(name = "STUD_REG", referencedColumnName = "regno"))
    private List<Student> students;

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
