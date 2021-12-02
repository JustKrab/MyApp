package com.example.myapp.controllers;

import com.example.myapp.entityes.Review;
import com.example.myapp.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
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
