package com.deslabs.school.security;


import com.deslabs.school.security.filter.CustomAuthenitcationfilter;
import com.deslabs.school.security.filter.CustomAuthenticationEntryPoint;
import com.deslabs.school.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource(name = "studentService")
    UserDetailsService userDetailsService;
    @Resource(name = "teacherService")
    UserDetailsService teacherService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        auth.userDetailsService(teacherService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //to chage login url
        CustomAuthenitcationfilter customAuthenitcationfilter = new CustomAuthenitcationfilter(authenticationManagerBean());
        customAuthenitcationfilter.setFilterProcessesUrl("/api/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**", "/api/register/**").permitAll()
                .antMatchers(GET, "/**").hasAnyAuthority("STUDENT")
                .antMatchers(PUT, "/api/**").hasAnyAuthority("STUDENT", "TEACHER")
                .antMatchers(POST, "/api/user/**").hasAnyAuthority("ROLE_USER")
                .anyRequest().authenticated().and()
                .addFilter(customAuthenitcationfilter)
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomAuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }
}
