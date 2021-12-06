package com.example.myapp.services;

import com.example.myapp.entities.Review;
import com.example.myapp.entities.ReviewPhotos;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewPhotoService {

    void uploadFile(MultipartFile file, Review review) throws IOException;

    List<ReviewPhotos> findPhotos(Long id);

    void removePhotoBuReviewId(Long id) throws Exception;

}
