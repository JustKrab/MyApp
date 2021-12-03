package com.example.myapp.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.myapp.entityes.Review;
import com.example.myapp.entityes.ReviewPhotos;
import com.example.myapp.repos.ReviewPhotoRepo;
import org.apache.maven.wagon.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ReviewPhotoService {

    @Value("${cloudinary.app}")
    private Cloudinary cloudinary;



    @Autowired
    private ReviewPhotoRepo reviewPhotoRepo;

    public void uploadFile(MultipartFile file, Review review) throws IOException {

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("use_filename", "true",
                            "unique_filename", "false","filename",resultFilename));

            ReviewPhotos reviewPhotos = new ReviewPhotos();
            reviewPhotos.setFilename(resultFilename);
            reviewPhotos.setReview(review);
            reviewPhotoRepo.save(reviewPhotos);

    }

    public List<ReviewPhotos> findPhotos(Long id){
        return reviewPhotoRepo.findByReviewId(id);
    }

    public void removePhotoBuReviewId(Long id) throws Exception {

        reviewPhotoRepo.removeByReviewId(id);
    }
}
