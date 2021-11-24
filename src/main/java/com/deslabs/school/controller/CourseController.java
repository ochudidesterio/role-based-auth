package com.deslabs.school.controller;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */

import com.deslabs.school.domain.Course;
import com.deslabs.school.response.ResponseHandler;
import com.deslabs.school.service.Courseservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {
    private final Courseservice service;

    @GetMapping("/courses")
    public ResponseEntity<Object>getAllCourses(){
        try{
          List<Course> courses=service.getAllCourses();
          return ResponseHandler.response("courses", HttpStatus.OK,courses);
        }catch (Exception e){
           return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @PostMapping("/register/course")
    public ResponseEntity<Object>createCourse(@RequestBody Course course){
        try{
           Course result = service.createCourse(course);
           return ResponseHandler.response("created",HttpStatus.CREATED,result);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @GetMapping("/course/{unit}")
    public ResponseEntity<Object>getCourse(@PathVariable Integer unit){
        try{
            Course course = service.getCourse(unit);
            return ResponseHandler.response("course",HttpStatus.OK,course);
        }catch (Exception e){
           return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @DeleteMapping("/delete/course/{unit}")
    public ResponseEntity<Object>deleteCourse(@PathVariable Integer unit){
        try{
            Object obj = service.deleteCourse(unit);
            return ResponseHandler.response("deleted",HttpStatus.OK,obj);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @PutMapping("/update/course/{unit}")
    public ResponseEntity<Object>updateCourse(@PathVariable Integer unit, @RequestBody Course course){
        try{
            Course result = service.updateCourse(unit, course);
            return ResponseHandler.response("updated",HttpStatus.CREATED,result);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @PutMapping("/add/student/{regno}/course/{unit}")
    public ResponseEntity<Object>addStudent(@PathVariable Integer regno, @PathVariable Integer unit){
        try{
           Course course = service.addStudent(regno, unit) ;
           return ResponseHandler.response("student added",HttpStatus.OK,course);
        }catch (Exception e){
           return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
}
