package com.example.Bradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bradesco.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    
}
