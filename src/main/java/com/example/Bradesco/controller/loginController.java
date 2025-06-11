package com.example.Bradesco.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Bradesco.model.Cartao;
import com.example.Bradesco.model.Conta;
import com.example.Bradesco.repository.ClienteRepository;
import com.example.Bradesco.repository.ContaRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    // o modificador final indica que a variável não pode ser alterada após a
    // inicialização
    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    // construtor que recebe o repositório de contas como parâmetro
    public LoginController(ContaRepository contaRepository,
            ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    // Método que mapeia a URL raiz ("/") para o método login()
    @GetMapping("/")
    public String login(@RequestParam(required = false) Integer agencia,
            @RequestParam(required = false) Integer numero_conta,
            Model model) {
        model.addAttribute("agencia", agencia);
        model.addAttribute("numero_conta", numero_conta);
        return "Login";
    }

    // Método que mapeia a URL "/login" para realizar o login
 @PostMapping("/login")
public String realizarLogin(@RequestParam Integer agencia, @RequestParam Integer numero_conta,
        @RequestParam String senha, Model model, HttpSession session) {
    Conta conta = contaRepository.LoginAgenciaConta(agencia, numero_conta, senha);

    if (conta != null) {
        session.setAttribute("contaLogada", conta);
        model.addAttribute("conta", conta);
        model.addAttribute("cliente", conta.getCliente());
        // Garante que cartoes nunca será null
        List<Cartao> cartoes = conta.getCartoes();
        if (cartoes == null) {
            cartoes = new ArrayList<>();
        }
        model.addAttribute("cartoes", cartoes); // Use a lista inicializada!
        return "/HomePg";
    } else {
        model.addAttribute("erro", "Dados da conta inválidos");
        return "/Login";
    }
}


}
