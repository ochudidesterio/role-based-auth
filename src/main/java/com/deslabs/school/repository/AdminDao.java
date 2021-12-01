package com.deslabs.school.repository;

import com.deslabs.school.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 *Author: Desterio
 *Date: 11/26/2021
 *Year: 2021
 */
public interface AdminDao extends JpaRepository<Admin,Integer> {
    @Query("select a from Admin a where a.email=:email")
    Admin findByEmail(@Param("email") String email);
}
