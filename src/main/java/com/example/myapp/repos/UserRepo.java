package com.example.myapp.repos;

import com.example.myapp.entityes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    User findByUsernameAndId(String username,Long id);

    User findUserById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);



}
