package com.example.Bradesco.controller;

import java.math.BigDecimal;
import java.time.LocalDate; // Importação correta
import java.time.LocalDateTime;

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
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PixController {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    @GetMapping("/Pix")
    public String mostrarPaginaPix() {
        return "PixPg";
    }


    @PostMapping("/pix")
    public String realizarPix(
            @RequestParam BigDecimal valor,
            @RequestParam String chavePix,
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) String confirmado,
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


        // Conte quantas transações já foram feitas para este destinatário
       LocalDateTime limite = LocalDateTime.now().minusHours(24);

        int transferenciasUltimas24h = transacaoRepository
            .countByContaOrigemIdContaAndContaDestinoIdContaAndDataHoraAfter(
                contaOrigem.getIdConta(), contaDestino.getIdConta(), limite);

        if (transferenciasUltimas24h >= 3 && confirmado == null) {
            model.addAttribute("erro", "Limite de 3 transferências para este destinatário em 24h atingido. Pix bloqueado.");
            return "PixPg";
        }

        //verifica se a conta pode ser uma bet

        int recebimentosMesmoValor = transacaoRepository
            .countByContaDestinoIdContaAndValorAndDataHoraAfter(
                contaDestino.getIdConta(), valor.doubleValue(), limite);        

        if (recebimentosMesmoValor > 10 && confirmado == null) {
            model.addAttribute("aviso", "Atenção: o destinatário recebeu mais de 10 vezes o mesmo valor nas últimas 24h. Pode ser atividade suspeita (ex: casa de apostas).");
        }


        // Conta suspeita (criada há menos de 7 dias)
        boolean contaSuspeita = contaDestino.getData_abertura().isAfter(LocalDate.now().minusDays(7));
        if (contaSuspeita && confirmado == null) {
            model.addAttribute("aviso", "Atenção: a conta de destino foi criada recentemente e pode ser suspeita.");
            model.addAttribute("dadosPix",new DadosPix(valor, chavePix, descricao));
            return "PixPg";
        }

        // Realiza transferência
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        Transacao transacao = new Transacao();
        transacao.setValor(valor.doubleValue());
        transacao.setDataHora(LocalDateTime.now());
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


