package com.example.myapp.controllers;

import com.example.myapp.entityes.Review;
import com.example.myapp.entityes.User;
import com.example.myapp.entityes.UserReviewRating;
import com.example.myapp.services.ReviewService;
import com.example.myapp.services.SearchService;
import com.example.myapp.services.UserReviewRatingService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserReviewRatingService userReviewRatingService;

    @GetMapping("/")
    public String greeting(Model model) {

        List<Review> last = reviewService.findLastAdded().stream()
                .peek(v -> v.setTitle(String.format("%s (%s ✪)", v.getTitle(), userReviewRatingService.usersRating(v.getId()))))
                .collect(Collectors.toList());

//        model.addAttribute("rated");
        model.addAttribute("last",last);

        return "index";
    }




    @GetMapping("/src")
    public String search(@RequestParam(value = "search", required = false) String q, Model model) {
        List<Review> searchResults = null;

        try {
            searchResults = searchService.fuzzySearch(q);

        } catch (Exception ex) {
            return "errorpage";
        }

        model.addAttribute("search", searchResults);
        return "search";
    }

}
