package com.example.Bradesco.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.Bradesco.model.Conta;


// Essa interface é um repositório para a entidade Produto, permitindo operações CRUD no banco de dados


public interface ContaRepository extends CrudRepository<Conta, Long> {


    
    // Método para buscar uma conta pelo número da conta e agência
    @Query("SELECT c FROM Conta c WHERE c.agencia = :agencia AND c.numero_conta = :numero_conta AND c.senha = :senha")
    Conta LoginAgenciaConta(@Param("agencia") Integer agencia,
                            @Param("numero_conta") Integer numero_conta,
                            @Param("senha") String senha);

}
