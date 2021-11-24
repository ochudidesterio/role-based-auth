package com.deslabs.school.controller;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */

import com.deslabs.school.domain.Department;
import com.deslabs.school.response.ResponseHandler;
import com.deslabs.school.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class DepartmentController {
    private final DepartmentService service;

    @GetMapping("/departments")
    public ResponseEntity<Object>getAllDepartments(){
        try{
            List<Department>departments= service.getAllDepartments();
            return ResponseHandler.response("departments", HttpStatus.OK,departments);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/department/{name}")
    public ResponseEntity<Object>getDepartments(@PathVariable String name){
        try{
            Department departments= service.getDepartment(name);
            return ResponseHandler.response("department", HttpStatus.OK,departments);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @DeleteMapping("/departments/{name}")
    public ResponseEntity<Object>deleteDepartments(@PathVariable String name){
        try{
            Object obj = service.deleteDepartment(name);
            return ResponseHandler.response("deleted", HttpStatus.OK,obj);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }


}
