package com.example.myapp.services;

import com.example.myapp.entities.Groups;
import com.example.myapp.entities.Review;
import com.example.myapp.entities.User;

import java.util.List;

public interface ReviewService {
    Review editReview(Long id, String text, String title, User author, String theme, String rating, Groups groups);
    Review add(String title, String text, User user, String theme, String rating, String group);
    List<Review> findReviewByAuthor(User user);
    Review findReviewById(Long id);
    void saveReview(Review review);
    void removeReviewById(Long id) throws Exception ;
    List<Review> findLastAdded();
    List<Review> findMostPopular();
    List<Review> findAll();
    List<Review> findBooks();
    List<Review> findGames();
    List<Review> findSerials();
    List<Review> findFilms();
}
