package com.example.Bradesco.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

import com.example.Bradesco.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    
    Optional<Cliente> findByCpf(String cpf);
}
