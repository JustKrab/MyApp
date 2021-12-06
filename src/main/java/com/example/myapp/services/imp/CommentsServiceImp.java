package com.example.myapp.services.imp;

import com.example.myapp.entities.Comments;
import com.example.myapp.entities.Review;
import com.example.myapp.entities.User;
import com.example.myapp.repos.CommentsRepo;
import com.example.myapp.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentsServiceImp implements CommentsService {

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
