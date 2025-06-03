package com.example.Bradesco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Bradesco.repository.ClienteRepository;
import com.example.Bradesco.repository.ContaRepository;

// Controllers no Spring Boot servem para mapear rotas HTTP e retornar páginas ou dados para o navegador.

@Controller
public class IndexController {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public IndexController(ContaRepository contaRepository,
                           ClienteRepository clienteRepository){
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/index")
    public String home() {
        return "PgInicial";
    }

}
