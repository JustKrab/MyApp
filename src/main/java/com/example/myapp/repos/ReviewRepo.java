package com.example.myapp.repos;

import com.example.myapp.entities.Groups;
import com.example.myapp.entities.Review;
import com.example.myapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

    Review findReviewById(Long id);

    List<Review> findReviewByAuthor(User author);

    List<Review> findReviewByTitle(Review title);

    void removeReviewById(Long id);

    List<Review> findReviewByGroups(Groups group);
}