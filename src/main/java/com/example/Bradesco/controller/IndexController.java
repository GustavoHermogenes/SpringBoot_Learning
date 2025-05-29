package com.example.Bradesco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// Controllers no Spring Boot servem para mapear rotas HTTP e retornar páginas ou dados para o navegador.

@Controller
public class IndexController {
 
    @GetMapping("/index")
    public String home() {
        return "index";
    }
    

}
