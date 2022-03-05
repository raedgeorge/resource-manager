package com.atech.mongodbapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @GetMapping("/index")
    public String showIndex(){

        return "products-home-page";
    }
}
