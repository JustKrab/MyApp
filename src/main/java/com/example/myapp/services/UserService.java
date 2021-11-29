package com.example.myapp.services;

import com.example.myapp.entityes.User;
import com.example.myapp.repos.ReviewRepo;
import com.example.myapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void saveUser(User user){

        userRepo.save(user);
    }

    public User findByUsernameAndId(String username,Long id){return userRepo.findByUsernameAndId(username,id);}



    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findUserByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public User findUserById(Long id){
        return userRepo.findUserById(id);
    }
}
