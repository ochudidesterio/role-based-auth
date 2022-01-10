package com.deslabs.school.service;

import com.deslabs.school.domain.Parent;
import com.deslabs.school.domain.Teacher;
import com.deslabs.school.repository.ParentDao;
import com.deslabs.school.repository.RoleDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
 *Author: Desterio
 *Date: 12/20/2021
 *Year: 2021
 */

@Service(value = "parentService")
@Transactional
@RequiredArgsConstructor
@Slf4j

public class ParentServiceImpl implements ParentService, UserDetailsService {
    private final ParentDao parentDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Parent registerParent(Parent parent) {
        parent.setPassword(passwordEncoder.encode(parent.getPassword()));
        parent.setRoles(List.of(roleDao.getById(1)));
        return parentDao.save(parent);
    }

    @Override
    public Parent getParent(Integer parentId) {
        return parentDao.findById(parentId).get();
    }

    @Override
    public void deleteParent(Integer parentId) {
parentDao.findById(parentId);
    }

    @Override
    public Parent updateParent(Parent parent) {
        return null;
    }

    @Override
    public List<Parent> getAllParents() {
        return parentDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Parent parent = parentDao.getByEmail(email);
        if (parent == null) {
            log.error("parent not found in database");
            throw new UsernameNotFoundException("parent not found");
        } else {
            log.info("Parent with email {} found", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        parent.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole_name())));

        return new User(parent.getEmail(), parent.getPassword(), authorities);
    }
}
