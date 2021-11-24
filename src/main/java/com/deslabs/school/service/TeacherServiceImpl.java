package com.deslabs.school.service;

import com.deslabs.school.domain.Department;
import com.deslabs.school.domain.Role;
import com.deslabs.school.domain.Teacher;
import com.deslabs.school.repository.DepartmentDao;
import com.deslabs.school.repository.RoleDao;
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
public class TeacherServiceImpl implements TeacherService {
    private final TeacherDao teacherDao;
    private final DepartmentDao departmentDao;
    private final RoleDao roleDao;
@Autowired
    public TeacherServiceImpl(TeacherDao teacherDao, DepartmentDao departmentDao, RoleDao roleDao) {
        this.teacherDao = teacherDao;
        this.departmentDao = departmentDao;
        this.roleDao = roleDao;
    }

    @Override
    public Teacher registerTeacher(Teacher teacher) {
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
        updateTeacher.setAge(teacher.getAge());
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
        Teacher teacher= getTeacher(teacherId);
        Role role = roleDao.findById(roleId).get();
        List<Role>roles = teacher.getRoles();
        roles.add(role);
        teacher.setRoles(roles);
        return teacherDao.save(teacher);
    }
}
