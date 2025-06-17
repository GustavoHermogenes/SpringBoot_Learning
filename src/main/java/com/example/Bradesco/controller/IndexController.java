package com.example.Bradesco.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.PageRequest;


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
    public String HomePg(HttpSession session, Model model) {

        Conta contaSessao = (Conta) session.getAttribute("contaLogada");
        if (contaSessao == null) {

            return "redirect:/login";
        }
        // Busque a conta do banco para garantir que é a mesma instância usada nas transações
        Conta conta = contaRepository.findById(contaSessao.getIdConta()).orElseThrow();

        model.addAttribute("conta", conta);
        model.addAttribute("cliente", conta.getCliente());
        List<Cartao> cartoes = conta.getCartoes();
        if (cartoes == null) {
            cartoes = new ArrayList<>();
        }
        model.addAttribute("cartoes", cartoes);

        // Busca PIX enviados e recebidos
        List<Transacao> historicoPix = transacaoRepository.findByContaOrigemIdContaOrContaDestinoIdContaOrderByDataHoraDesc(conta.getIdConta(), conta.getIdConta(), PageRequest.of(0, 10));
        model.addAttribute("historicoPix", historicoPix);
        System.out.println("Transações encontradas: " + historicoPix.size());
        System.out.println(conta.getIdConta());
        for (Transacao t : historicoPix) {
            System.out.println("ID: " + t.getId() + " | Origem: " + t.getContaOrigem().getIdConta() + " | Destino: " + t.getContaDestino().getIdConta());
        }

        return "HomePg";
    }

}   