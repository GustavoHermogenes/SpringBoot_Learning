package com.example.Bradesco.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bradesco.model.Conta;
import com.example.Bradesco.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    List<Transacao> findByContaOrigem(Conta contaOrigem);
    List<Transacao> findByContaDestino(Conta contaDestino);



    List<Transacao> findByContaOrigemIdContaOrContaDestinoIdContaOrderByDataHoraDesc(Long origemId, Long destinoId, Pageable pageable);

    int countByContaOrigemIdContaAndContaDestinoIdContaAndDataHoraAfter(Long origemId, Long destinoId, LocalDateTime dataLimite);

    int countByContaDestinoIdContaAndValorAndDataHoraAfter(Long destinoId, Double valor, LocalDateTime dataLimite);

    List<Transacao> findByContaOrigemOrContaDestino(Conta contaOrigem, Conta contaDestino);
}
