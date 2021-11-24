package com.deslabs.school.service;

import com.deslabs.school.domain.Course;
import com.deslabs.school.domain.Student;
import com.deslabs.school.repository.CourseDao;
import com.deslabs.school.repository.StudentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */

@Transactional
@Service
public class CourseServiceImpl implements Courseservice {
    private final CourseDao courseDao;
    private final StudentDao studentDao;
@Autowired
    public CourseServiceImpl(CourseDao courseDao, StudentDao studentDao) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
    }

    @Override
    public Course createCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    @Override
    public Course getCourse(Integer unit) {
        return courseDao.findById(unit).get();
    }

    @Override
    public Course updateCourse(Integer unit, Course course) {
        Course updateCourse = getCourse(unit);
        updateCourse.setName(course.getName());
        updateCourse.setUnit(course.getUnit());
        updateCourse.setStudents(course.getStudents());
        return courseDao.save(updateCourse);
    }

    @Override
    public Object deleteCourse(Integer unit) {
        courseDao.deleteById(unit);
        return null;
    }

    @Override
    public Course addStudent(Integer regno, Integer unit) {
        Student student = studentDao.getById(regno);
        Course course = getCourse(unit);
        List<Student>studentList=course.getStudents();
        studentList.add(student);
        course.setStudents(studentList);
        return courseDao.save(course);
    }
}
