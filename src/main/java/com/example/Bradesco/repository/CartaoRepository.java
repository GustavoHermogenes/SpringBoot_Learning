package com.example.Bradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Bradesco.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    
    boolean existsByNumeroCartao(String numeroCartao);

}
