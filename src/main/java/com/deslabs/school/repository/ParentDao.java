package com.deslabs.school.repository;

import com.deslabs.school.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
 *Author: Desterio
 *Date: 12/20/2021
 *Year: 2021
 */
public interface ParentDao extends JpaRepository<Parent,Integer> {
    @Query("SELECT p from Parent  p where p.email=:email")
    Parent getByEmail(String email);
}
