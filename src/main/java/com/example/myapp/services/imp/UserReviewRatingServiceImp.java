package com.example.myapp.services.imp;


import com.example.myapp.entities.Review;
import com.example.myapp.entities.User;
import com.example.myapp.entities.UserReviewRating;
import com.example.myapp.repos.UserReviewRatingRepo;
import com.example.myapp.services.UserReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewRatingServiceImp implements UserReviewRatingService {

    @Autowired
    private UserReviewRatingRepo userReviewRatingRepo;


    public void estimate(User user, String rating, String liked, Review review) {

        UserReviewRating userReviewRating = new UserReviewRating(rating, liked, user, review);
        userReviewRatingRepo.save(userReviewRating);
    }

    public void removeById(Long id) {
        userReviewRatingRepo.removeByReviewId(id);
    }

    public UserReviewRating findByUser(User user, Long id) {
        return userReviewRatingRepo.findByUserAndReviewId(user, id);
    }

    public Integer likesCount(Long id) {
        List<UserReviewRating> all = userReviewRatingRepo.findByReviewId(id);
        return all.stream()
                .mapToInt(value -> Integer.parseInt(value.getLiked()))
                .sum();

    }

    public Integer allLikes(User user) {
        List<UserReviewRating> all = userReviewRatingRepo.findByUser(user);
        return all.stream()
                .mapToInt(value -> Integer.parseInt(value.getLiked()))
                .sum();

    }

    public String usersRating(Long id) {
        List<UserReviewRating> all = userReviewRatingRepo.findByReviewId(id);
        double average = all.stream()
                .mapToInt(value -> Integer.parseInt(value.getUserRating()))
                .average()
                .orElse(-10);
        return average == -10 ? "0" : String.format("%.2f", average);
    }

    public List<UserReviewRating> findAllByReviewId(Long id) {
        return userReviewRatingRepo.findByReviewId(id);
    }


}
