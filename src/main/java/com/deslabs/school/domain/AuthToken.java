package com.deslabs.school.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
 *Author: Desterio
 *Date: 11/23/2021
 *Year: 2021
 */

public class AuthToken {
    private String token;

    public AuthToken() {
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
