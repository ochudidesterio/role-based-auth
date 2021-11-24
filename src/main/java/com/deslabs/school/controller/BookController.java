package com.deslabs.school.controller;

import com.deslabs.school.domain.Book;
import com.deslabs.school.response.ResponseHandler;
import com.deslabs.school.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping("/register/book")
    public ResponseEntity<Object>registerBook(@RequestBody Book book){
        try{
            Book result = service.registerBook(book);
            return ResponseHandler.response("registered", HttpStatus.CREATED,result);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @GetMapping("/books")
    public ResponseEntity<Object>getAllBooks(){
        try{
            List<Book>books = service.getAllBooks();
            return ResponseHandler.response("Books", HttpStatus.OK,books);
        }catch (Exception e){
           return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Object>getBooks(@PathVariable Integer id){
        try{
            Book book = service.getBook(id);
            return ResponseHandler.response("Book", HttpStatus.OK,book);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @DeleteMapping("/book/{id}")
    public ResponseEntity<Object>deleteBooks(@PathVariable Integer id){
        try{
            Object obj = service.deleteBook(id);
            return ResponseHandler.response("deleted", HttpStatus.OK,obj);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @PutMapping("/add/course/{unit}/book/{id}")
    public ResponseEntity<Object>addCourse(@PathVariable Integer id, @PathVariable Integer unit){
        try{
            Book book = service.addCourse(id, unit);
            return ResponseHandler.response("deleted", HttpStatus.OK,book);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
}
