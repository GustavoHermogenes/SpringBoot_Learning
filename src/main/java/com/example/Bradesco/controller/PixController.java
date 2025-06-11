package com.example.Bradesco.controller;

import java.math.BigDecimal;
import java.time.LocalDate; // Importação correta

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Importação correta

import com.example.Bradesco.model.Conta;
import com.example.Bradesco.model.Transacao;
import com.example.Bradesco.repository.ContaRepository;
import com.example.Bradesco.repository.TransacaoRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PixController {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    @PostMapping("/pix")
    public String realizarPix(
            @RequestParam BigDecimal valor,
            @RequestParam String chavePix,
            @RequestParam(required = false) String descricao,
            HttpSession session,
            Model model) {

        // Pegue a conta do remetente da sessão (exemplo)
        Conta contaOrigem = (Conta) session.getAttribute("contaLogada");
        if (contaOrigem == null) {
            model.addAttribute("erro", "Usuário não autenticado.");
            return "PixPg";
        }

        // Busque a conta destino pela chave Pix
        Conta contaDestino = contaRepository.findByChavePix(chavePix);
        if (contaDestino == null) {
            model.addAttribute("erro", "Conta de destino não encontrada.");
            return "PixPg";
        }

        // Bloqueio por valor
        if (valor.compareTo(BigDecimal.valueOf(1000)) > 0) {
            model.addAttribute("erro", "Valor máximo para Pix é R$ 1.000,00.");
            return "PixPg";
        }

        // Saldo suficiente?
        if (contaOrigem.getSaldo().compareTo(valor) < 0) {
            model.addAttribute("erro", "Saldo insuficiente.");
            return "PixPg";
        }

        // Conta suspeita (criada há menos de 7 dias)
        boolean contaSuspeita = contaDestino.getData_abertura().isAfter(LocalDate.now().minusDays(7));
        if (contaSuspeita) {
            model.addAttribute("aviso", "Atenção: a conta de destino foi criada recentemente e pode ser suspeita.");
        }

        // Realiza transferência
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        Transacao transacao = new Transacao();
        transacao.setValor(valor.doubleValue());
        transacao.setDataHora(java.time.LocalDateTime.now().toString());
        transacao.setTipo("PIX");
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestino(contaDestino);
        transacaoRepository.save(transacao);
            
        // Passa dados para página de sucesso
        model.addAttribute("sucesso", "PIX enviado com sucesso!");
        model.addAttribute("valor", valor);
        model.addAttribute("remetente", contaOrigem.getCliente().getNome());
        model.addAttribute("destinatario", contaDestino.getCliente().getNome());

        return "sucessoPix";
    }
}