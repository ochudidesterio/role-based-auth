package com.deslabs.school.controller;

/*
 *Author: Desterio
 *Date: 11/20/2021
 *Year: 2021
 */

import com.deslabs.school.domain.Sports;
import com.deslabs.school.response.ResponseHandler;
import com.deslabs.school.service.SportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SportsController {
    private final SportsService service;

    @GetMapping("/sports")
    public ResponseEntity<Object>getAllSports(){
        try{
            List<Sports>sports= service.getAllSports();
            return ResponseHandler.response("sports", HttpStatus.OK,sports);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @GetMapping("/sports/{sportId}")
    public ResponseEntity<Object>getSport(@PathVariable Integer sportId){
        try{
            Sports sports= service.getSports(sportId);
            return ResponseHandler.response("sport", HttpStatus.OK,sports);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }
    @PostMapping("/register/sport")
    public ResponseEntity<Object>registerSport(@RequestBody Sports sports){
        try{
            Sports sport= service.registerSport(sports);
            return ResponseHandler.response("Created", HttpStatus.CREATED,sport);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @DeleteMapping("/delete/sport/{sportId}")
    public ResponseEntity<Object>deleteSport(@PathVariable Integer sportId){
        try{
            Object obj= service.deleteSport(sportId);
            return ResponseHandler.response("deleted", HttpStatus.OK,obj);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @PutMapping("/add/student/{regno}/sport/{sportId}")
    public ResponseEntity<Object>addStudent(@PathVariable Integer regno, @PathVariable Integer sportId){
        try{
            Sports sports = service.addStudent(regno, sportId);
            return ResponseHandler.response("Student added", HttpStatus.OK,sports);
        }catch (Exception e){
            return ResponseHandler.response(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

}
