package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.common.Constants;
import com.example.demo.models.User;
import com.example.demo.service.UserService;


@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/custom-login")
    public String loadLoginPage(){
        return "login";
    }

    @PostMapping("/signup")
    public String login(@ModelAttribute("signup") User user){
        String token = userService.signUp(user);
        return UriComponentsBuilder.fromUriString(Constants.homeUrl)
                .queryParam("auth_token", token)
                .build().toUriString();
    }

}
