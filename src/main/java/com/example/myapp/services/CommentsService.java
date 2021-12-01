package com.example.myapp.services;

import com.example.myapp.entityes.Comments;
import com.example.myapp.entityes.Review;
import com.example.myapp.entityes.User;
import com.example.myapp.repos.CommentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepo commentsRepo;


    public void addComment(User user, Review review, String text){
        LocalDate time = LocalDate.now();
        Comments comment = new Comments(text,time,review,user);
        commentsRepo.save(comment);
    }

    public List<Comments> findCommentsReview(Long id){
        return commentsRepo.findByReviewId(id);
    }

    public void removeByReviewId(Long id){
        commentsRepo.removeByReviewId(id);
    }
}
