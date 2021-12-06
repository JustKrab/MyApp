package com.example.myapp.services;

import com.example.myapp.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void saveUser(User user);

    List<User> findAll();

    User findUserByUsername(String username);

    User findUserById(Long id);

}
