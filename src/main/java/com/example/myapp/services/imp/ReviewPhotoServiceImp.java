package com.example.myapp.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.myapp.entities.Review;
import com.example.myapp.entities.ReviewPhotos;
import com.example.myapp.repos.ReviewPhotoRepo;
import com.example.myapp.services.ReviewPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewPhotoServiceImp implements ReviewPhotoService {

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
