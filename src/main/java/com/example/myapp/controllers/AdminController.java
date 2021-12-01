package com.example.myapp.controllers;

import com.example.myapp.entityes.Review;
import com.example.myapp.entityes.User;
import com.example.myapp.services.ReviewPhotoService;
import com.example.myapp.services.ReviewService;
import com.example.myapp.services.UserProfileService;
import liquibase.util.file.FilenameUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/userlist")
public class AdminController {


    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewPhotoService reviewPhotoService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {

        model.addAttribute("users", userProfileService.findAll());

        return "userlist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}")
    public String editUser(@PathVariable String username,
                           Model model,
                           @AuthenticationPrincipal User watcher) {

        model.addAttribute("user", userProfileService.findUserByUsername(username));
        model.addAttribute("reviews", userProfileService.findReviewByAuthor(userProfileService.findUserByUsername(username)));

        return "profile";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{username}/addreview")
    public String UserReview(@PathVariable String username) {
        return "addreview";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{username}/addreview")
    public String addUserReview(@PathVariable String username,
                                @RequestParam String text,
                                @RequestParam String theme,
                                @RequestParam(defaultValue = "1") String rating,
                                @RequestParam String title,
                                @RequestParam String group,
                                @RequestParam("file") MultipartFile[] files,
                                Map<String, Object> model
    ) throws IOException {

        User user = userProfileService.findUserByUsername(username);

        if (Strings.isEmpty(text)
                || Strings.isEmpty(theme)
                || Strings.isEmpty(rating)
                || Strings.isEmpty(title)
                || Strings.isEmpty(group)) {
            model.put("message", "One or more fields are empty!");
            return "addreview";
        }

        Review review = reviewService.add(title, text, user, theme, rating, group);

        List<MultipartFile> filtered = Arrays.stream(files).filter(e -> !Objects.requireNonNull(e.getOriginalFilename()).equals("")).collect(Collectors.toList());
        if (!filtered.isEmpty()) {
            for (MultipartFile file : filtered) {
                String ext = FilenameUtils.getExtension(file.getOriginalFilename());
                if (!"jpg".equalsIgnoreCase(ext)
                        && !"png".equalsIgnoreCase(ext)) {
                    model.put("message", "Invalid format of upload files!");
                    return "addreview";
                }
                reviewPhotoService.uploadFile(file, review);
            }
        }


        return "redirect:/userlist/{username}";
    }


}
