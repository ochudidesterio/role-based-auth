package com.deslabs.school.exceptions;

/*
 *Author: Desterio
 *Date: 11/22/2021
 *Year: 2021
 */
public class ApiRequestExceptions extends RuntimeException {
    public ApiRequestExceptions(String message) {
        super(message);
    }

    public ApiRequestExceptions(String message, Throwable cause) {
        super(message, cause);
    }

}
