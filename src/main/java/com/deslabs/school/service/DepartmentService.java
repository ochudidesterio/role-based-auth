package com.deslabs.school.service;

import antlr.collections.impl.LList;
import com.deslabs.school.domain.Department;

import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface DepartmentService {
    Department registerDepartment(Department department);
    List<Department>getAllDepartments();
    Department getDepartment(String name);
    Object deleteDepartment(String name);

}
