package com.deslabs.school.service;

import com.deslabs.school.domain.Book;

import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
public interface BookService {
    Book registerBook(Book book);
    List<Book>getAllBooks();
    Book getBook(Integer id);
    Object deleteBook(Integer id);
    Book addCourse(Integer unit, Integer id);
}
