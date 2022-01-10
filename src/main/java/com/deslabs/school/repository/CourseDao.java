package com.deslabs.school.repository;

import com.deslabs.school.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface CourseDao extends JpaRepository<Course,Integer> {
    @Query("select c from Course c join c.students s where s.regno=:regno")
    List<Course>findByStudents(int regno);
}
