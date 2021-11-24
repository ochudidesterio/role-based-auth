package com.deslabs.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/*
 *Author: Desterio
 *Date: 11/22/2021
 *Year: 2021
 */
@ControllerAdvice
public class ApiExceptionHandler {

@ExceptionHandler(value = {ApiRequestExceptions.class})
    public ResponseEntity<Object>handleNotFoundException(ApiRequestExceptions e){
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException,HttpStatus.NOT_FOUND);
    }
}
