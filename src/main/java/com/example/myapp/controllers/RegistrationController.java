package com.example.myapp.controllers;

import com.example.myapp.entityes.User;
import com.example.myapp.services.RegistrationService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(Map<String, Object> model,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam String username) {

        User user = new User(username, email, password);

        if (Strings.isEmpty(email) || Strings.isEmpty(password) || Strings.isEmpty(username)) {
            model.put("userExists", "One or more fields are empty.");
            return "registration";
        }
        if (!registrationService.addUser(user)) {
            model.put("userExists", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }


}
