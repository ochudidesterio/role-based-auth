package com.deslabs.school.controller;

import com.deslabs.school.domain.Department;
import com.deslabs.school.domain.Teacher;
import com.deslabs.school.response.ResponseHandler;
import com.deslabs.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
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
public class TeacherController {
    private final TeacherService service;

    @GetMapping("/teachers")
    public ResponseEntity<Object>getAllTeachers(){
        try{
            List<Teacher>teachers= service.getAllTeachers();
            return ResponseHandler.response("teachers", HttpStatus.OK,teachers);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<Object>registerTeacher(@RequestBody Teacher teacher){
        try{
            Teacher result= service.registerTeacher(teacher);
            return ResponseHandler.response("created", HttpStatus.CREATED,result);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Object>getTeacher(@PathVariable Integer teacherId){
        try{
            Teacher result= service.getTeacher(teacherId);
            return ResponseHandler.response("teacher", HttpStatus.CREATED,result);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @DeleteMapping("/delete/teacher/{teacherId}")
    public ResponseEntity<Object>deleteTeacher(@PathVariable Integer teacherId){
        try{
            Object result= service.deleteTeacher(teacherId);
            return ResponseHandler.response("deleted", HttpStatus.CREATED,result);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @PutMapping("/update/teacher/{teacherId}")
    public ResponseEntity<Object>updateTeacher(@RequestBody Teacher teacher, @PathVariable Integer teacherId){
        try{
            Teacher result= service.updateTeacher(teacherId, teacher);
            return ResponseHandler.response("updated", HttpStatus.CREATED,result);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @PutMapping("/add/teacher/{teacherId}/department/{name}")
    public ResponseEntity<Object>addTeacher(@PathVariable String name, @PathVariable Integer teacherId){
        try{
            Teacher teacher = service.addTeachertoDepartment(name, teacherId);
            return ResponseHandler.response("Teacher added", HttpStatus.OK,teacher);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
}
