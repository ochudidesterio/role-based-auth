package com.deslabs.school.controller;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */

import com.deslabs.school.domain.StudyYear;
import com.deslabs.school.response.ResponseHandler;
import com.deslabs.school.service.StudyYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudyYearController {
    private final StudyYearService service;

    @GetMapping("/studyYears")
    public ResponseEntity<Object>getAllYears(){
        try{
            List<StudyYear>studyYears= service.getAllYears();
            return ResponseHandler.response("study years", HttpStatus.OK,studyYears);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @PostMapping("/register/studyYear")
    public ResponseEntity<Object>registerYear(@RequestBody StudyYear studyYear){
        try{
            StudyYear year= service.createStudyYear(studyYear);
            return ResponseHandler.response("Created", HttpStatus.CREATED,year);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @PutMapping("/add/teacher/{teacherId}/year/{id}")
    public ResponseEntity<Object>addTeacher(@PathVariable Integer teacherId, @PathVariable Integer id){
        try{
            StudyYear year= service.addTeacher(teacherId, id);
            return ResponseHandler.response("Teacher Added", HttpStatus.CREATED,year);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @PutMapping("/add/student/{regno}/year/{id}")
    public ResponseEntity<Object>addStudent(@PathVariable Integer regno, @PathVariable Integer id){
        try{
            StudyYear year= service.addStudent(regno, id);
            return ResponseHandler.response("Student Added", HttpStatus.CREATED,year);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
}
