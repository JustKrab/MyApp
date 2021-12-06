package com.example.myapp.services;

import com.example.myapp.entities.Comments;
import com.example.myapp.entities.Review;
import com.example.myapp.entities.User;

import java.util.List;

public interface CommentsService {

     void addComment(User user, Review review, String text);

     List<Comments> findCommentsReview(Long id);

     void removeByReviewId(Long id) throws Exception;
}
