package com.example.myapp.services;


import com.example.myapp.entityes.Review;
import com.example.myapp.entityes.User;
import com.example.myapp.entityes.UserReviewRating;
import com.example.myapp.repos.UserReviewRatingRepo;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewRatingService {

    @Autowired
    private UserReviewRatingRepo userReviewRatingRepo;


    public void estimate(User user, String rating, String liked, Review review){


        UserReviewRating userReviewRating = new UserReviewRating(rating,liked,user,review);
        userReviewRatingRepo.save(userReviewRating);
    }

    public UserReviewRating findByUser(User user,Long id){
        return userReviewRatingRepo.findByUserAndReviewId(user,id);
    }

    public Integer likesCount(Long id){
       List<UserReviewRating> all = userReviewRatingRepo.findByReviewId(id);
       int count = 0;
       for (UserReviewRating urr:all){
           if (urr.getLiked().equals("1")){
               count++;
           }
       }
       return count;
    }

    public String usersRating(Long id){
        List<UserReviewRating> all = userReviewRatingRepo.findByReviewId(id);
        int count = 0;
        int i = 0;
        for (UserReviewRating urr:all){
            count = count + Integer.parseInt(urr.getUserRating());
            i++;
        }
        double avg =(double)count/i;
        return String.format("%.2f",avg);
    }

    public  List<UserReviewRating> findAllByReviewId(Long id){
        return userReviewRatingRepo.findByReviewId(id);
    }

}
