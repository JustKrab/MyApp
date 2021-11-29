package com.example.myapp.repos;


import com.example.myapp.entityes.User;
import com.example.myapp.entityes.UserReviewRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReviewRatingRepo extends JpaRepository<UserReviewRating, Long> {

    UserReviewRating findByUser(User user);

    List<UserReviewRating> findByReviewId(Long id);


}
