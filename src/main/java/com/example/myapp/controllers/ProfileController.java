package com.example.myapp.controllers;


import com.example.myapp.entityes.User;
import com.example.myapp.services.UserProfileService;
import liquibase.util.file.FilenameUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserProfileService userProfileService;


    @GetMapping
    public String getProfile(Model model,
                             @AuthenticationPrincipal User user
                             ) {

        model.addAttribute("user", userProfileService.findById(user.getId()));
        model.addAttribute("reviews", userProfileService.findReviewByAuthor(user));

        return "profile";
    }


    @GetMapping("/{id}/edit")
    public String EditProfile(Model model,
                              @PathVariable Long id,
                              @AuthenticationPrincipal User user) {

        model.addAttribute("user", userProfileService.findUserByUsername(user.getUsername()));

        return "editprofile";
    }

    @PostMapping("/{id}/edit")
    public String editReview(
            @PathVariable Long id,
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String passwordConfirm,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {


        if (userProfileService.findAll().contains(userProfileService.findUserByUsername(username)) && !user.getUsername().equals(username)) {
            model.put("message", "User with this username exists!");
            return "redirect:/profile/{id}/edit";
        }
        if (Strings.isEmpty(passwordConfirm))
            passwordConfirm = password;

        if (Strings.isEmpty(username)
                || Strings.isEmpty(email)
                || Strings.isEmpty(password)
        ) {
            model.put("message", "One or more fields are empty!");
//            return "editprofile";
            return "redirect:/profile/{id}/edit";
        }

        if (!password.equals(passwordConfirm)) {
            model.put("message", "The password doesn't match");
            return "redirect:/profile/{id}/edit";
        }


        String ext = FilenameUtils.getExtension(file.getName());

        if (!ext.isEmpty()) {
            if (!"jpg".equalsIgnoreCase(ext)
                    && !"png".equalsIgnoreCase(ext)) {
                model.put("message", "Invalid format of upload files!");
                return "redirect:/profile/{id}/edit";
            }
        }

       userProfileService.editProfile(id, username, email, password, file, user);

        return "redirect:/profile";
    }



    @GetMapping("/{username}")
    public String getUsersProfile(@PathVariable String username,
                                  Model model,
                                  @AuthenticationPrincipal User watcher) {

        User user = userProfileService.findUserByUsername(username);

        if (user.getUsername().equals(watcher.getUsername())) {
            return "redirect:/profile";
        }

        model.addAttribute("user", user);
        model.addAttribute("reviews", userProfileService.findReviewByAuthor(user));


        return "viewprofile";
    }


}
