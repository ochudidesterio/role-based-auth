package com.deslabs.school.domain;

import javax.persistence.*;

/*
 *Author: Desterio
 *Date: 12/21/2021
 *Year: 2021
 */
@Entity
public class StudentMarks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String score;
    private String term;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "regno")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "unit")
    private Course course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
