package com.example.myapp.services.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.myapp.entities.Review;
import com.example.myapp.entities.User;
import com.example.myapp.services.ReviewService;
import com.example.myapp.services.UserProfileService;
import com.example.myapp.services.UserReviewRatingService;
import com.example.myapp.services.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImp implements UserProfileService {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserReviewRatingService userReviewRatingService;

    @Value("${cloudinary.app}")
    private Cloudinary cloudinary;


    public List<Review> findReviewByAuthor(User user) {
        return reviewService.findReviewByAuthor(user).stream()
                .peek(v -> v.setTitle(String.format("%s (%s ✪)", v.getTitle(), userReviewRatingService.usersRating(v.getId()))))
                .collect(Collectors.toList());
    }

    public User findById(Long id) {
        return userService.findUserById(id);
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

        userService.saveUser(user);


    }


}
