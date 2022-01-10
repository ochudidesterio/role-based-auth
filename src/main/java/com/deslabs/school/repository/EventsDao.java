package com.deslabs.school.repository;

import com.deslabs.school.domain.Events;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 *Author: Desterio
 *Date: 12/21/2021
 *Year: 2021
 */
public interface EventsDao extends JpaRepository<Events,Integer> {
}
