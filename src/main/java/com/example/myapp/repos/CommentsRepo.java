package com.example.myapp.repos;

import com.example.myapp.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepo extends JpaRepository<Comments, Long> {

    List<Comments> findByReviewId(Long id);

    List<Comments> findByUserId(Long id);

    void removeByReviewId(Long id);
}
