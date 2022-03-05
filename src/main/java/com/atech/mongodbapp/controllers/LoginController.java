package com.atech.mongodbapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/loginPage")
    public String showLoginPage(){

        return "login/login-Page";
    }
}
