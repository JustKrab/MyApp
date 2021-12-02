package com.example.myapp.services;

import com.example.myapp.entityes.Groups;
import com.example.myapp.entityes.Review;
import com.example.myapp.entityes.User;
import com.example.myapp.repos.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ReviewPhotoService reviewPhotoService;

    @Autowired
    private CommentsService commentsService;

    public Review editReview(Long id, String text, String title, User author, String theme, String rating, Groups groups) {


        Review review = new Review(id, title, text, author, theme, rating, groups);
        return reviewRepo.save(review);
    }

    public Review add(String title, String text, User user, String theme, String rating, String group) {


        Review review = new Review(title, text, user, theme, rating);
        review.setGroups(Groups.valueOf(group));
        return reviewRepo.save(review);
    }

    public List<Review> findReviewByAuthor(User user) {
        return reviewRepo.findReviewByAuthor(user);
    }



    public Review findReviewById(Long id) {
        return reviewRepo.findReviewById(id);
    }

    public void saveReview(Review review) {
        reviewRepo.save(review);
    }

    public void removeReviewById(Long id) throws Exception {

        commentsService.removeByReviewId(id);
        reviewPhotoService.removePhotoBuReviewId(id);
        reviewRepo.removeReviewById(id);
    }

    public List<Review> findAll() {
        return reviewRepo.findAll();
    }
}
