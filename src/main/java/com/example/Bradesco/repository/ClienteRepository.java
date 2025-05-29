package com.example.Bradesco.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Bradesco.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
