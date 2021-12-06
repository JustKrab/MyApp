package com.example.myapp.services;

import com.example.myapp.entities.Review;
import com.example.myapp.entities.User;
import com.example.myapp.entities.UserReviewRating;

import java.util.List;

public interface UserReviewRatingService {

    void estimate(User user, String rating, String liked, Review review);

    void removeById(Long id);

    Integer likesCount(Long id);

    Integer allLikes(User user);

    String usersRating(Long id);

    List<UserReviewRating> findAllByReviewId(Long id);

    UserReviewRating findByUser(User user, Long id);
}
