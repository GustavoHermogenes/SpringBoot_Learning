package com.example.Bradesco;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")

public class helloController {
    

    @GetMapping("/hello")
    public String hello(@RequestParam String param) {
        return "bem vindo ao sping boot";
    }
    


}
