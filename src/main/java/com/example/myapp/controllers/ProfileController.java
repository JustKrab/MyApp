package com.example.myapp.controllers;


import com.example.myapp.entities.User;
import com.example.myapp.services.UserProfileService;
import com.example.myapp.services.UserReviewRatingService;
import com.example.myapp.services.UserService;
import liquibase.util.file.FilenameUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserReviewRatingService userReviewRatingService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getProfile(Model model,
                             Principal principal
                             ) {

        User usr = (User) userService.loadUserByUsername(principal.getName());
        usr.setUsername(String.format("%s (%s ❤)",usr.getUsername(),userReviewRatingService.allLikes(usr)));

        model.addAttribute("usr", usr);
        model.addAttribute("reviews", userProfileService.findReviewByAuthor(usr));

        return "profile";
    }


    @GetMapping("/{id}/edit")
    public String EditProfile(Model model,
                              @PathVariable Long id) {

        model.addAttribute("user", userService.findUserById(id));

        return "editprofile";
    }

    @PostMapping("/{id}/edit")
    public String editReview(
            @PathVariable Long id,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String passwordConfirm,
            @RequestParam("file") MultipartFile file,
            Model model) throws IOException {


        if (userService.findAll().contains(userService.findUserByUsername(username)) && !userProfileService.findById(id).getUsername().equals(username)) {
            model.addAttribute("message", "User with this username exists!");
            model.addAttribute("user", userService.findUserById(id));
            return "editprofile";
        }
        if (Strings.isEmpty(passwordConfirm))
            passwordConfirm = password;

        if (Strings.isEmpty(username)
                || Strings.isEmpty(email)
                || Strings.isEmpty(password)
        ) {
            model.addAttribute("message", "One or more fields are empty!");
            model.addAttribute("user", userService.findUserById(id));
            return "editprofile";
        }

        if (!password.equals(passwordConfirm)) {
            model.addAttribute("message", "The password doesn't match");
            model.addAttribute("user", userService.findUserById(id));
            return "editprofile";
        }

        String ext = FilenameUtils.getExtension(file.getOriginalFilename());

        if (ext != null && !ext.isEmpty()) {
            if (!"jpg".equalsIgnoreCase(ext)
                    && !"png".equalsIgnoreCase(ext)) {
                model.addAttribute("message", "Invalid format of upload files!");
                model.addAttribute("user", userService.findUserById(id));
                return "editprofile";
            }
        }

        userProfileService.editProfile(id, username, email, password, file, userProfileService.findById(id));

        return "redirect:/profile";
    }



    @GetMapping("/{username}")
    public String getUsersProfile(@PathVariable String username,
                                  Model model,
                                  @AuthenticationPrincipal User watcher) {

        User usr = userService.findUserByUsername(username);
        usr.setUsername(String.format("%s (%s ❤)",usr.getUsername(),userReviewRatingService.allLikes(usr)));

        if (usr.getUsername().equals(watcher.getUsername())) {
            return "redirect:/profile";
        }

        model.addAttribute("usr", usr);
        model.addAttribute("reviews", userProfileService.findReviewByAuthor(userService.findUserByUsername(username)));

        return "viewprofile";
    }


}
