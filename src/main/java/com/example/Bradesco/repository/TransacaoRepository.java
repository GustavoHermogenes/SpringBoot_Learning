package com.example.Bradesco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Bradesco.model.Conta;
import com.example.Bradesco.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    List<Transacao> findByContaOrigem(Conta contaOrigem);
    List<Transacao> findByContaDestino(Conta contaDestino);


    List<Transacao> findByContaOrigemOrContaDestino(Conta contaOrigem, Conta contaDestino);
}
