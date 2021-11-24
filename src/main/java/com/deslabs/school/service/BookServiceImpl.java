package com.deslabs.school.service;

import com.deslabs.school.domain.Book;
import com.deslabs.school.domain.Course;
import com.deslabs.school.repository.BookDao;
import com.deslabs.school.repository.CourseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */

@Service
@Transactional
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;
    private final CourseDao courseDao;
@Autowired
    public BookServiceImpl(BookDao bookDao, CourseDao courseDao) {
        this.bookDao = bookDao;
        this.courseDao = courseDao;
    }

    @Override
    public Book registerBook(Book book) {
        return bookDao.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDao.findById(id).get();
    }

    @Override
    public Object deleteBook(Integer id) {
    bookDao.deleteById(id);
    return null;
    }

    @Override
    public Book addCourse(Integer unit, Integer id) {
        Course course = courseDao.getById(unit);
        Book book= getBook(id);
        book.setCourse(course);
        return bookDao.save(book);
    }
}
