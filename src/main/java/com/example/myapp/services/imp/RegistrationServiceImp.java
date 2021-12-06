package com.example.myapp.services.imp;

import com.example.myapp.entities.Role;
import com.example.myapp.entities.User;
import com.example.myapp.services.RegistrationService;
import com.example.myapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class RegistrationServiceImp implements RegistrationService {

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
