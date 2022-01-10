package com.deslabs.school.service;

import com.deslabs.school.domain.Role;
import com.deslabs.school.domain.Student;
import com.deslabs.school.repository.RoleDao;
import com.deslabs.school.repository.StudentDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;


    @Override
    public List<Student> getAllStudents() {
        log.info("all students");
        return studentDao.findAll();
    }

    @Override
    public Student registerStudent(Student student) {



        log.info("student saved");
        return studentDao.save(student);
    }

    @Override
    public Student getStudent(Integer regno) {
        return studentDao.findById(regno).get();
    }

    @Override
    public Student updateStudent(Integer regno, Student student) {
        Student updateStudent = getStudent(regno);
        updateStudent.setDob(student.getDob());
        updateStudent.setFee(student.getFee());
        updateStudent.setFee_paid(student.getFee_paid());
        updateStudent.setLast_name(student.getLast_name());
        updateStudent.setFirst_name(student.getFirst_name());
        updateStudent.setGender(student.getGender());
        updateStudent.setRegno(student.getRegno());
        return studentDao.save(updateStudent);
    }

    @Override
    public Object deleteStudent(Integer regno) {
        studentDao.deleteById(regno);
        return null;
    }



}
