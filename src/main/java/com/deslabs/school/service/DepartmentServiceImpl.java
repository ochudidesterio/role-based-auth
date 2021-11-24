package com.deslabs.school.service;

import com.deslabs.school.domain.Department;
import com.deslabs.school.repository.DepartmentDao;
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
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentDao departmentDao;
    private final TeacherDao teacherDao;
@Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao, TeacherDao teacherDao) {
        this.departmentDao = departmentDao;
        this.teacherDao = teacherDao;
    }

    @Override
    public Department registerDepartment(Department department) {
        return departmentDao.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.findAll();
    }

    @Override
    public Department getDepartment(String name) {
        return departmentDao.findById(name).get();
    }

    @Override
    public Object deleteDepartment(String name) {
        departmentDao.deleteById(name);
        return  null;
    }


}
