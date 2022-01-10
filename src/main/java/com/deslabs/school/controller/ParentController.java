package com.deslabs.school.controller;

import com.deslabs.school.domain.Parent;
import com.deslabs.school.domain.Teacher;
import com.deslabs.school.response.ResponseHandler;
import com.deslabs.school.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *Author: Desterio
 *Date: 12/20/2021
 *Year: 2021
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService service;

    @GetMapping("/parents")
    public ResponseEntity<Object> getAllTeachers(){
        try{
            List<Parent> parents= service.getAllParents();
            return ResponseHandler.response("parents", HttpStatus.OK,parents);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @PostMapping("/register/parent")
    public ResponseEntity<Object> registerParent(@RequestBody Parent parent){
        try{
           Parent parents= service.registerParent(parent);
            return ResponseHandler.response("parent", HttpStatus.CREATED,parents);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

}
