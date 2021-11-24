package com.deslabs.school.service;

import com.deslabs.school.domain.Role;

import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface RoleService {
    Role createRole(Role role);
    Role getRole(Integer roleId);
    List<Role>getAllRoles();
    void deleteRole(Integer roleId);
    Role findByName(String name);
}
