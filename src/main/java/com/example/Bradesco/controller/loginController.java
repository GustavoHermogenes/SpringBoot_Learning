package com.example.Bradesco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Bradesco.model.Conta;
import com.example.Bradesco.repository.ContaRepository;


@Controller
public class LoginController {

    // o modificador final indica que a variável não pode ser alterada após a inicialização
    private final ContaRepository contaRepository;

    public LoginController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }
    

    @GetMapping("/")
    public String login() {
        return "login";
    }
    

    @GetMapping("/login")
    public String realizarLogin(@RequestParam Integer agencia, @RequestParam Integer numero_conta, @RequestParam String senha, Model model) {
        Conta conta = contaRepository.LoginAgenciaConta(agencia, numero_conta, senha);
    
        if (conta != null) {
            model.addAttribute("conta", conta);
            model.addAttribute("cliente", conta.getCliente());
            return "/index";
        }else{
            model.addAttribute("erro", "Dados da conta inválidos");
            return "login";
        }
    }
    
    

}
