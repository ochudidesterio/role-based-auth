package com.deslabs.school.service;

import com.deslabs.school.domain.Role;
import com.deslabs.school.domain.Student;
import com.deslabs.school.repository.RoleDao;
import com.deslabs.school.repository.StudentDao;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
@Transactional
@Service(value = "userService")
@Slf4j
public class StudentServiceImpl implements StudentService,UserDetailsService {

    private final StudentDao studentDao;
    private final RoleDao roleDao;
@Autowired
    public StudentServiceImpl(StudentDao studentDao, RoleDao roleDao) {
        this.studentDao = studentDao;
        this.roleDao = roleDao;

    }

    @Override
    public List<Student> getAllStudents() {
        log.info("all students");
        return studentDao.findAll();
    }

    @Override
    public Student registerStudent(Student student) {
//student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentDao.save(student);
    }

    @Override
    public Student getStudent(Integer regno) {
        return studentDao.findById(regno).get();
    }

    @Override
    public Student updateStudent(Integer regno,Student student) {
        Student updateStudent =getStudent(regno);
        updateStudent.setAge(student.getAge());
        updateStudent.setFee(student.getFee());
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
        List<Role>roles= student.getRoles();
        roles.add(role);
        student.setRoles(roles);
        return studentDao.save(student);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentDao.getStudentByEmail(email);
        if(student == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(), getAuthority(student));
    }
    private List<SimpleGrantedAuthority> getAuthority(Student student) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        student.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole_name()));
        });
        return authorities;
    }
}
