package com.example.myapp.repos;


import com.example.myapp.entityes.User;
import com.example.myapp.entityes.UserReviewRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReviewRatingRepo extends JpaRepository<UserReviewRating, Long> {

    UserReviewRating findByUserAndReviewId(User user,Long id);

    List<UserReviewRating> findByReviewId(Long id);

    List<UserReviewRating> findByUser(User user);

    void removeByReviewId(Long id);
}
