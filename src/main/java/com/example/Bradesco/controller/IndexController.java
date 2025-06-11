package com.example.Bradesco.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Bradesco.model.Cartao;
import com.example.Bradesco.model.Conta;
import com.example.Bradesco.model.Transacao;
import com.example.Bradesco.repository.ClienteRepository;
import com.example.Bradesco.repository.ContaRepository;
import com.example.Bradesco.repository.TransacaoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository; // Adicione isso

    public IndexController(ContaRepository contaRepository,
                           ClienteRepository clienteRepository,
                           TransacaoRepository transacaoRepository) { // Adicione isso
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository; // Adicione isso
    }

    @GetMapping("/HomePg")
    public String voltarParaHome(HttpSession session, Model model) {
        Conta conta = (Conta) session.getAttribute("contaLogada");
        if (conta == null) {
            return "redirect:/login";
        }
        model.addAttribute("conta", conta);
        model.addAttribute("cliente", conta.getCliente());
        List<Cartao> cartoes = conta.getCartoes();
        if (cartoes == null) {
            cartoes = new ArrayList<>();
        }
        model.addAttribute("cartoes", cartoes);

        // Busca PIX enviados e recebidos
        List<Transacao> historicoPix = transacaoRepository.findByContaOrigemOrContaDestino(conta, conta);
        model.addAttribute("historicoPix", historicoPix);
        System.out.println("Transações encontradas: " + historicoPix.size());

        return "HomePg";
    }

    @GetMapping("/PixPg")
    public String PixPg() {
        return "PixPg";
    }
}