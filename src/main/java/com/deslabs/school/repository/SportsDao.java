package com.deslabs.school.repository;

import com.deslabs.school.domain.Sports;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface SportsDao extends JpaRepository<Sports,Integer> {
}
