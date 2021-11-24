package com.deslabs.school.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/*
 *Author: Desterio
 *Date: 11/22/2021
 *Year: 2021
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiException {
    private String message;
    private HttpStatus status;
    private ZonedDateTime zonedDateTime;
}
