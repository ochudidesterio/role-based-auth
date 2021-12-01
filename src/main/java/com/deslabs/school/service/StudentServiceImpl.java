package com.deslabs.school.service;

import com.deslabs.school.domain.Role;
import com.deslabs.school.domain.Student;
import com.deslabs.school.repository.RoleDao;
import com.deslabs.school.repository.StudentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
@Transactional
@Service(value = "studentService")
@Slf4j
public class StudentServiceImpl implements StudentService, UserDetailsService {

    private final StudentDao studentDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao, RoleDao roleDao, PasswordEncoder pass) {
        this.studentDao = studentDao;
        this.roleDao = roleDao;
        this.passwordEncoder = pass;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student user = studentDao.getStudentByEmail(username);
        if (user == null) {
            log.error("Student  not found in db");
            throw new UsernameNotFoundException("User not found in db");
        } else {
            log.info("Student {} found in db", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole_name())));
        return new User(user.getEmail(), user.getPassword(), authorities);
       // return StudentDetailImpl.build(user);
    }

    @Override
    public List<Student> getAllStudents() {
        log.info("all students");
        return studentDao.findAll();
    }

    @Override
    public Student registerStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));

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
        updateStudent.setAge(student.getAge());
        updateStudent.setFee(student.getFee());
        updateStudent.setPassword(student.getPassword());
        updateStudent.setEmail(student.getEmail());
        updateStudent.setFee_paid(student.getFee_paid());
        updateStudent.setLast_name(student.getLast_name());
        updateStudent.setFirst_name(student.getFirst_name());
        updateStudent.setGender(student.getGender());
        updateStudent.setStatus(student.getStatus());
        updateStudent.setRegno(student.getRegno());
        return studentDao.save(updateStudent);
    }

    @Override
    public Object deleteStudent(Integer regno) {
        studentDao.deleteById(regno);
        return null;
    }

    @Override
    public Student addRole(Integer roleId, Integer regno) {
        Student student = getStudent(regno);
        Role role = roleDao.findById(roleId).get();
        List<Role> roles = student.getRoles();
        roles.add(role);
        student.setRoles(roles);
        return studentDao.save(student);
    }

}
