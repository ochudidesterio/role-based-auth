package com.deslabs.school.repository;

import com.deslabs.school.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {
    @Query("SELECT r FROM Role r WHERE r.role_name = :rolename")
    Role findByRole_name(String rolename);
}
