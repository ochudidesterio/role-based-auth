package com.deslabs.school.controller;

import com.deslabs.school.domain.Student;
import com.deslabs.school.exceptions.ApiRequestExceptions;
import com.deslabs.school.response.ResponseHandler;
import com.deslabs.school.service.StudentService;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService service;



    @GetMapping("/students")
    public ResponseEntity<Object> getAllStudents() {
        try {
            List<Student> result = service.getAllStudents();
            return ResponseHandler.response("All students", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/register/student")
    public ResponseEntity<Object> registerStudent(@RequestBody Student student) {
        try {
            Student result = service.registerStudent(student);
            return ResponseHandler.response("registered",HttpStatus.CREATED, result);
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping("/update/student/{regno}")
    public ResponseEntity<Object> updateStudent(@PathVariable Integer regno, @RequestBody Student student) {
        try {
            Student result = service.updateStudent(regno, student);
            return ResponseHandler.response("updated",HttpStatus.CREATED, result);
        } catch (Exception e) {
            throw new ApiRequestExceptions(e.getMessage());
        }
    }

    @GetMapping("/student/{regno}")
    public ResponseEntity<Object> getStudent(@PathVariable Integer regno) {
        try {
            Student result = service.getStudent(regno);
            return ResponseHandler.response("student",HttpStatus.OK, result);
        } catch (Exception e) {
            throw new ApiRequestExceptions(e.getMessage());
        }
    }

    @DeleteMapping("/delete/student/{regno}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer regno) {
        try {
            Object result = service.deleteStudent(regno);
            return ResponseHandler.response("deleted",HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS, null);
        }
    }

}
