package com.deslabs.school.repository;

import com.deslabs.school.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface TeacherDao extends JpaRepository<Teacher,Integer> {
    @Query("select t from Teacher t where t.email=:email")
    Teacher findByEmail(@Param("email") String email);
}
