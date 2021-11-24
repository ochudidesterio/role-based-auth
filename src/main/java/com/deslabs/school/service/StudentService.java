package com.deslabs.school.service;

import com.deslabs.school.domain.Student;

import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface StudentService {
    //Student Service Methods
    List<Student>getAllStudents();
    Student registerStudent(Student student);
    Student getStudent(Integer regno);
    Student updateStudent(Integer regno,Student student);
    Object deleteStudent(Integer regno);
    Student addRole(Integer roleId, Integer regno);
}
