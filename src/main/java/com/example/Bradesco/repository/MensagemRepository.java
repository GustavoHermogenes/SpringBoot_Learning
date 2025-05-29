package com.example.Bradesco.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MensagemRepository {
    
    public String obterMensagem(){
        return "olá do repositorio";
    }

}
