package com.example.myapp.services;

import com.example.myapp.entities.Review;
import com.example.myapp.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserProfileService {

    List<Review> findReviewByAuthor(User user);

    User findById(Long id);

    void editProfile(Long id, String username, String email, String password, MultipartFile file, User eUser) throws IOException;


}
