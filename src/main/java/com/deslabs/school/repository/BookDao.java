package com.deslabs.school.repository;

import com.deslabs.school.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface BookDao extends JpaRepository<Book,Integer> {
}
