package com.example.myapp.controllers;

import com.example.myapp.entityes.Groups;
import com.example.myapp.entityes.Review;
import com.example.myapp.entityes.User;
import com.example.myapp.entityes.UserReviewRating;
import com.example.myapp.services.CommentsService;
import com.example.myapp.services.ReviewPhotoService;
import com.example.myapp.services.ReviewService;
import com.example.myapp.services.UserReviewRatingService;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import liquibase.util.file.FilenameUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/review")
public class ReviewController {


    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewPhotoService reviewPhotoService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private UserReviewRatingService userReviewRatingService;

    @GetMapping("/{id}")
    public String review(@PathVariable Long id, Model model) {

        if (reviewService.findReviewById(id) != null) {
            model.addAttribute("review", reviewService.findReviewById(id));
            model.addAttribute("photos", reviewPhotoService.findPhotos(id));
            model.addAttribute("comments", commentsService.findCommentsReview(id));
            model.addAttribute("likes",userReviewRatingService.likesCount(id));
            model.addAttribute("rating",userReviewRatingService.usersRating(id));
            model.addAttribute("counts",userReviewRatingService.findAllByReviewId(id).size());
            return "review";
        }
        return "redirect:/";
    }

    @PostMapping("/{id}")
    public String reviewComment(@PathVariable Long id,
                                @Nullable String text,
                                @Nullable String liked,
                                @Nullable String rating,
                                @AuthenticationPrincipal User user,
                                Model model) {
        @Nullable
        UserReviewRating userReviewRating = userReviewRatingService.findByUser(user);

        if (!Strings.isEmpty(liked) && !Strings.isEmpty(rating) && userReviewRating == null) {
            userReviewRatingService.estimate(user, rating, liked, reviewService.findReviewById(id));
        }
//        if (!(userReviewRating == null)){
//            model.addAttribute("rated","U just already rate this review");
//        }
        model.addAttribute("review", reviewService.findReviewById(id));
        model.addAttribute("photos", reviewPhotoService.findPhotos(id));
        if (Strings.isEmpty(text) && Strings.isEmpty(liked) && Strings.isEmpty(rating)) {
            model.addAttribute("empty", "Comment is empty!");
            model.addAttribute("comments", commentsService.findCommentsReview(id));
            return "review";
        }
        if (!Strings.isEmpty(text))
            commentsService.addComment(user, reviewService.findReviewById(id), text);

        model.addAttribute("comments", commentsService.findCommentsReview(id));
        model.addAttribute("likes",userReviewRatingService.likesCount(id));
        model.addAttribute("rating",userReviewRatingService.usersRating(id));
        model.addAttribute("counts",userReviewRatingService.findAllByReviewId(id).size());

        return "review";
    }

    @GetMapping("/add/new")
    public String review() {
        return "addreview";
    }

    @PostMapping("/add/new")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String theme,
            @RequestParam(defaultValue = "1") String rating,
            @RequestParam String title,
            @RequestParam String group,
            @RequestParam("file") MultipartFile[] files,
            Map<String, Object> model
    ) throws IOException {


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


        return "redirect:/profile";
    }

    @Transactional
    @GetMapping("/{id}/delete")
    public String deleteR(@PathVariable Long id) throws Exception {

        reviewService.removeReviewById(id);

        return "redirect:/profile";
    }


    @GetMapping("/{id}/edit")
    public String reviewEditForm(@PathVariable Long id, Model model) {

        model.addAttribute("review", reviewService.findReviewById(id));
        model.addAttribute("photos", reviewPhotoService.findPhotos(id));

        return "revieweditor";
    }


    @PostMapping("/{id}/edit")
    public String editReview(@PathVariable Long id,
                             @RequestParam String text,
                             @RequestParam String title,
                             @RequestParam String group,
                             @RequestParam String theme,
                             @RequestParam(defaultValue = "1") String rating,
                             @AuthenticationPrincipal User user,
                             @RequestParam("file") MultipartFile[] files,
                             Map<String, Object> model) throws IOException {


        if (Strings.isEmpty(text)
                || Strings.isEmpty(theme)
                || Strings.isEmpty(rating)
                || Strings.isEmpty(title)
                || Strings.isEmpty(group)) {
            model.put("message", "One or more fields are empty!");
            return "revieweditor";
        }
        Review original = reviewService.findReviewById(id);
        Review review = reviewService.editReview(id, text, title, original.getAuthor(), theme, rating, Groups.valueOf(group));

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

        if (user.isAdmin()) {
            return "redirect:/userlist";
        }

        return "redirect:/profile";
    }


    @GetMapping("/all")
    public String allReviews(Model model) {


        model.addAttribute("reviews", reviewService.findAll());
        return "allreviews";
    }
}
