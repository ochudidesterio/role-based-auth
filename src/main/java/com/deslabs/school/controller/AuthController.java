package com.deslabs.school.controller;

/*
 *Author: Desterio
 *Date: 11/24/2021
 *Year: 2021
 */

import com.deslabs.school.config.TokenProvider;
import com.deslabs.school.domain.AuthToken;
import com.deslabs.school.domain.LoginUser;
import com.deslabs.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthController {
    @Value("${jwt.token.prefix}")
    public String TOKEN_PREFIX;


    private final AuthenticationManager authenticationManager;


    private final TokenProvider jwtTokenUtil;


    private final StudentService userService;
@Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenProvider jwtTokenUtil, StudentService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

}
