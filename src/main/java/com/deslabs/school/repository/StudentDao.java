package com.deslabs.school.repository;

import com.deslabs.school.domain.Course;
import com.deslabs.school.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */

public interface StudentDao extends JpaRepository<Student,Integer> {

}
