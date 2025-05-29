package com.example.Bradesco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class loginController {
    

    @GetMapping("/")
    public String login(@RequestParam String param) {
        return new String();
    }
    

}
