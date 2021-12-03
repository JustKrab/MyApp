package com.example.myapp.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.myapp.entityes.Review;
import com.example.myapp.entityes.ReviewPhotos;
import com.example.myapp.entityes.User;
import org.apache.logging.log4j.util.Strings;
import org.codehaus.plexus.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class UserProfileService extends UserService {

    @Autowired
    private ReviewService reviewService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${cloudinary.app}")
    private Cloudinary cloudinary;


    public List<Review> findReviewByAuthor(User user) {
        return reviewService.findReviewByAuthor(user);
    }

    public User findById(Long id) {
        return findUserById(id);
    }

    public void editProfile(Long id, String username, String email, String password, MultipartFile file, User eUser) throws IOException {

        if (Strings.isEmpty(password))
            password = eUser.getPassword();

        String filename = file.getName();
        if (filename.equals("file") || Strings.isEmpty(filename)) {
            filename = eUser.getAvatar();
        }
        User user = new User(id, username, email, passwordEncoder.encode(password), filename);


        if (!file.isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();


            user.setAvatar(resultFilename);

            cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("use_filename", "true",
                            "unique_filename", "false", "filename", resultFilename));
        }

        user.setActive(true);
        user.setRoles(eUser.getRoles());

        saveUser(user);


    }


}
