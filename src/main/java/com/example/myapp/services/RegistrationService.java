package com.example.myapp.services;

import com.example.myapp.entityes.Role;
import com.example.myapp.entityes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class RegistrationService {

    @Autowired
    private UserService userService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addUser(User user) {
        User userFromDb = userService.findUserByUsername(user.getUsername());

        if (userFromDb != null ) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAvatar("user_r0qibf.png");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));


        userService.saveUser(user);

        return true;
    }


}
