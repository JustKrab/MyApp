package com.example.myapp.repos;


import com.example.myapp.entityes.Review;
import com.example.myapp.entityes.ReviewPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewPhotoRepo extends JpaRepository<ReviewPhotos, Long> {

    void removeByReviewId(Long id);

    List<ReviewPhotos> findByReviewId(Long id);
}
