package com.deslabs.school.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.deslabs.school.response.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenitcationfilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private Algorithm algorithm;
    private String refresh_token;
    private String access_token;

    public  CustomAuthenitcationfilter(AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       String username=request.getParameter("username");
       String password= request.getParameter("password");
       log.info("Username is {}",username);
       log.info("Password is {}",password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        UserDetails user = (UserDetails) authentication.getPrincipal();
       // TeacherDetailImpl teacher = (TeacherDetailImpl) authentication.getPrincipal();



        algorithm = Algorithm.HMAC256("secret".getBytes());

           List<String> roles = user.getAuthorities().stream()
                   .map(item -> item.getAuthority())
                   .collect(Collectors.toList());

           access_token = JWT.create().
                   withSubject(user.getUsername())
                   .withExpiresAt(new Date(System.currentTimeMillis()+ 100*60*1000))
                   .withIssuer(request.getRequestURL().toString())
                   .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                   .sign(algorithm);
            refresh_token = JWT.create().
                   withSubject(user.getUsername())
                   .withExpiresAt(new Date(System.currentTimeMillis()+ 1000*60*1000))
                   .withIssuer(request.getRequestURL().toString())
                   .sign(algorithm);

           response.setHeader("access_token",access_token);
           response.setHeader("refresh_token",refresh_token);
        LoginResponse loginResponse = new LoginResponse(user.getUsername(),roles,access_token,refresh_token);


           Map<String,Object> userResponse= new HashMap<>();
//        tokens.put("access_token",access_token);
//        tokens.put("refresh_token",refresh_token);
           userResponse.put("response", loginResponse);
           response.setContentType(APPLICATION_JSON_VALUE);
           new ObjectMapper().writeValue(response.getOutputStream(),userResponse);
       }

}
