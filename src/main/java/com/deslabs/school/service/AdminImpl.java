package com.deslabs.school.service;

import com.deslabs.school.domain.Admin;
import com.deslabs.school.repository.AdminDao;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

/*
 *Author: Desterio
 *Date: 11/26/2021
 *Year: 2021
 */
@Transactional
@Slf4j
@Service(value = "adminService")
@RequiredArgsConstructor
public class AdminImpl implements UserDetailsService {
    private final AdminDao adminDao;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminDao.findByEmail(email);
        if(admin == null){
           log.error("Admin not found");
           throw  new UsernameNotFoundException("Admin not found");
        }
        Collection<SimpleGrantedAuthority>authorities = new ArrayList<>();

        return null;
    }
}
