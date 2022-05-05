package com.example.world_phone.service.impl;


import com.example.world_phone.entity.StaffEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffDetailsServiceImpl implements UserDetailsService {
    private final StaffServiceImpl staffService;

    @Autowired
    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        StaffEntity staffEntity = staffService.getByEmail(email);
        if(staffEntity == null){
            log.error("User not found! " + email);
            session.setAttribute("emailLogin", email);
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }

        // Add info logged user to Session
        session.setAttribute("user", staffEntity);

        List<String> roleNames = new ArrayList<>();
        roleNames.add(staffEntity.getRole());

        List<GrantedAuthority> grantList = new ArrayList<>();
        for (String role : roleNames) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantList.add(authority);
        }
        return new User(staffEntity.getEmail(), staffEntity.getPassword(), grantList);
    }
}
