package com.example.Bradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bradesco.model.Produto;


// Essa interface é um repositório para a entidade Produto, permitindo operações CRUD no banco de dados

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Aqui você pode adicionar métodos personalizados, se necessário
    
}
