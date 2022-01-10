package com.deslabs.school.service;

import com.deslabs.school.domain.Department;
import com.deslabs.school.domain.Role;
import com.deslabs.school.domain.Teacher;
import com.deslabs.school.repository.DepartmentDao;
import com.deslabs.school.repository.RoleDao;
import com.deslabs.school.repository.TeacherDao;
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
@Service(value = "teacherService")
@Slf4j
public class TeacherServiceImpl implements TeacherService, UserDetailsService {
    private final TeacherDao teacherDao;
    private final DepartmentDao departmentDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao, DepartmentDao departmentDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.teacherDao = teacherDao;
        this.departmentDao = departmentDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Teacher registerTeacher(Teacher teacher) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacher.setRoles(List.of(roleDao.getById(2)));
        return teacherDao.save(teacher);
    }

    @Override
    public Teacher getTeacher(Integer teacherId) {
        return teacherDao.getById(teacherId);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.findAll();
    }

    @Override
    public Teacher updateTeacher(Integer teacherId, Teacher teacher) {
        Teacher updateTeacher = getTeacher(teacherId);
        updateTeacher.setTeacher_Id(teacher.getTeacher_Id());
        updateTeacher.setPhone_number(teacher.getPhone_number());
        updateTeacher.setSurname(teacher.getSurname());
        updateTeacher.setDob(teacher.getDob());
        updateTeacher.setGender(teacher.getGender());
        updateTeacher.setCourses(teacher.getCourses());
        updateTeacher.setFirst_name(teacher.getFirst_name());
        updateTeacher.setLast_name(teacher.getLast_name());
        updateTeacher.setStatus(teacher.getStatus());
        updateTeacher.setRoles(teacher.getRoles());
        updateTeacher.setDepartment(teacher.getDepartment());
        updateTeacher.setStudyYear(teacher.getStudyYear());
        return teacherDao.save(updateTeacher);
    }

    @Override
    public Object deleteTeacher(Integer teacherId) {
        teacherDao.deleteById(teacherId);
        return null;
    }

    @Override
    public Teacher addTeachertoDepartment(String name, Integer teacherId) {

        Teacher teacher = getTeacher(teacherId);

        Department department = departmentDao.findById(name).get();

        teacher.setDepartment(department);
        return teacherDao.save(teacher);

    }

    @Override
    public Teacher addRole(Integer roleId, Integer teacherId) {
        Teacher teacher = getTeacher(teacherId);
        Role role = roleDao.findById(roleId).get();
        List<Role> roles = teacher.getRoles();
        roles.add(role);
        teacher.setRoles(roles);
        return teacherDao.save(teacher);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teacherDao.findByEmail(email);
        if (teacher == null) {
            log.error("teacher not found in database");
            throw new UsernameNotFoundException("teacher not found");
        } else {
            log.info("Teacher with email {} found", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        teacher.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole_name())));

        // return  TeacherDetailImpl.build(teacher);
        return new User(teacher.getEmail(), teacher.getPassword(), authorities);
    }
}
