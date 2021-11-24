package com.deslabs.school.domain;

import lombok.Data;

/*
 *Author: Desterio
 *Date: 11/23/2021
 *Year: 2021
 */

public class LoginUser {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
