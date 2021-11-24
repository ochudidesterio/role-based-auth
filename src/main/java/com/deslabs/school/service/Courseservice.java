package com.deslabs.school.service;

import com.deslabs.school.domain.Course;

import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface Courseservice {
    Course createCourse(Course course);
    List<Course>getAllCourses();
    Course getCourse(Integer unit);
    Course updateCourse(Integer unit,Course course);
    Object deleteCourse(Integer unit);
    Course addStudent(Integer regno, Integer unit);
}
