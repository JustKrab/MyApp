package com.example.myapp.services.imp;

import com.example.myapp.entities.User;
import com.example.myapp.repos.UserRepo;
import com.example.myapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImp implements UserDetailsService, UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepo.findByUsername(username);
        if(user != null){
            return user;
        }
        throw new
                UsernameNotFoundException("User not exist with name :" +username);


    }

    public void saveUser(User user){

        userRepo.save(user);
    }


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
