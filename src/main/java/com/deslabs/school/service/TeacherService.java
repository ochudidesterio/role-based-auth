package com.deslabs.school.service;

import com.deslabs.school.domain.Department;
import com.deslabs.school.domain.Teacher;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface TeacherService {
    Teacher registerTeacher(Teacher teacher);
    Teacher getTeacher(Integer teacherId);
    List<Teacher>getAllTeachers();
    Teacher updateTeacher(Integer teacherId, Teacher teacher);
    Object deleteTeacher(Integer teacherId);
    Teacher addTeachertoDepartment(String name, Integer teacherId);
    Teacher addRole(Integer roleId, Integer teacherId);
}
