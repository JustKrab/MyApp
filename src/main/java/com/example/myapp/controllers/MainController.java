package com.example.myapp.controllers;

import com.example.myapp.entities.Review;
import com.example.myapp.services.ReviewService;
import com.example.myapp.services.SearchService;
import com.example.myapp.services.UserReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Qualifier("searchServiceImp")
    @Autowired
    private SearchService searchService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserReviewRatingService userReviewRatingService;

    @GetMapping("/")
    public String greeting(Model model) {

        model.addAttribute("popular",reviewService.findMostPopular());
        model.addAttribute("last",reviewService.findLastAdded());

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
