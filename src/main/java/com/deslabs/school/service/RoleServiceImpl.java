package com.deslabs.school.service;

import com.deslabs.school.domain.Role;
import com.deslabs.school.repository.RoleDao;
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
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
@Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role createRole(Role role) {
        return null;
    }

    @Override
    public Role getRole(Integer roleId) {
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public void deleteRole(Integer roleId) {

    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByRole_name(name);
    }
}
