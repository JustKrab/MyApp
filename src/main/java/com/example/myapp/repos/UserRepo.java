package com.example.myapp.repos;

import com.example.myapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    User findByUsernameAndId(String username,Long id);

    User findUserById(Long id);

    User findByUsername(String username);

    Optional<User> findByEmail(String email);



}
