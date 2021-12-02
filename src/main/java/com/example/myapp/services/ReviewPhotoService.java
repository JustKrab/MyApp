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
import java.util.UUID;

@Service
public class ReviewPhotoService {

    @Value("${cloudinary.app}")
    private Cloudinary cloudinary;

    @Value("${upload.path}")
    private String uploadPath;


    @Autowired
    private ReviewPhotoRepo reviewPhotoRepo;

    public void uploadFile(MultipartFile file, Review review) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));


            cloudinary.uploader().upload(uploadPath + "\\" + resultFilename,
                    ObjectUtils.asMap("use_filename", "true",
                            "unique_filename", "false"));
//            cloudinary.url().transformation(new Transformation().width(70).height(53).crop("scale")).imageTag(resultFilename);
            FileUtils.fileDelete(uploadPath + "\\" + resultFilename);

            ReviewPhotos reviewPhotos = new ReviewPhotos();
            reviewPhotos.setFilename(resultFilename);
            reviewPhotos.setReview(review);
            reviewPhotoRepo.save(reviewPhotos);
        }
    }

    public List<ReviewPhotos> findPhotos(Long id){
        return reviewPhotoRepo.findByReviewId(id);
    }

    public void removePhotoBuReviewId(Long id) throws Exception {
//        cloudinary.api().deleteResources("v1637790934/9320e6d3-a93f-42a2-8bd0-3015d4afbf2f.Gift.jpg"),
//                ObjectUtils.emptyMap();
        reviewPhotoRepo.removeByReviewId(id);
    }
}
