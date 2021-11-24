package com.deslabs.school.service;

import com.deslabs.school.domain.Student;
import com.deslabs.school.domain.StudyYear;
import com.deslabs.school.domain.Teacher;
import com.deslabs.school.repository.StudentDao;
import com.deslabs.school.repository.StudyYearDao;
import com.deslabs.school.repository.TeacherDao;
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
public class StudyYearImpl implements StudyYearService{
private final StudyYearDao studyYearDao;
private final TeacherDao teacherDao;
private final StudentDao studentDao;
@Autowired
    public StudyYearImpl(StudyYearDao studyYearDao, TeacherDao teacherDao, StudentDao studentDao) {
        this.studyYearDao = studyYearDao;
        this.teacherDao = teacherDao;
        this.studentDao = studentDao;
    }

    @Override
    public StudyYear createStudyYear(StudyYear studyYear) {
        return studyYearDao.save(studyYear);
    }

    @Override
    public List<StudyYear> getAllYears() {
        return studyYearDao.findAll();
    }

    @Override
    public StudyYear addStudent(Integer regno, Integer id) {
        Student student = studentDao.getById(regno);
        StudyYear studyYear = studyYearDao.findById(id).get();
        List<Student>students=studyYear.getStudents();
        students.add(student);
        studyYear.setStudents(students);
        return studyYearDao.save(studyYear);
    }

    @Override
    public StudyYear addTeacher(Integer teacherId, Integer id) {
        Teacher teacher = teacherDao.findById(teacherId).get();
        StudyYear studyYear = studyYearDao.findById(id).get();
        studyYear.setTeacher(teacher);
        return studyYearDao.save(studyYear);
    }
}
