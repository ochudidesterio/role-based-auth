package com.deslabs.school.security;



import com.deslabs.school.security.filter.CorsFilter;
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
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.annotation.Resource;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "parentService")
    UserDetailsService parentService;
    @Resource(name = "teacherService")
    UserDetailsService teacherService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(teacherService).passwordEncoder(bCryptPasswordEncoder);
        auth.userDetailsService(parentService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //to chage login url
        CustomAuthenitcationfilter customAuthenitcationfilter = new CustomAuthenitcationfilter(authenticationManagerBean());
        customAuthenitcationfilter.setFilterProcessesUrl("/api/login");


        http.cors();
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**", "/api/register/**","/**").permitAll()
                .antMatchers(GET, "/**").hasAnyAuthority("STUDENT")
                .antMatchers(PUT, "/api/**").hasAnyAuthority("STUDENT", "TEACHER")
                .antMatchers(POST, "/api/user/**").hasAnyAuthority("ROLE_USER")
                .anyRequest().authenticated().and()
                .addFilter(customAuthenitcationfilter)
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
               //.addFilterBefore(corsFilter(), SessionManagementFilter.class)
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


//
//@Bean
public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    //configuration.setAllowedOrigins(List.of("*"));
    configuration.addAllowedOriginPattern("*");
    configuration.addExposedHeader("Authorization");
    configuration.setAllowedMethods(List.of("HEAD",
            "GET", "POST", "PUT", "DELETE", "PATCH"));
    // setAllowCredentials(true) is important, otherwise:
    // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
    configuration.setAllowCredentials(true);
    // setAllowedHeaders is important! Without it, OPTIONS preflight request
    // will fail with 403 Invalid CORS request
    configuration.setAllowedHeaders(List.of("*"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}



}
