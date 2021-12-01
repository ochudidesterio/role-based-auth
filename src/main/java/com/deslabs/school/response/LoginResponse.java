package com.deslabs.school.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/*
 *Author: Desterio
 *Date: 11/29/2021
 *Year: 2021
 */
@Data
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private List<String>roles;
    private String access_token;
    private String refresh_token;
}
