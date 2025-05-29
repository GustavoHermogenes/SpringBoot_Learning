package com.example.Bradesco.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_cliente;

    private String nome;
    
    @Column(length = 14)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String endereco;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Column(length = 15)
    private String telefone;

    @Enumerated(EnumType.STRING)
    private status status;

    public enum status {
        ATIVO, INATIVO
    }


    // relacionamento com a entidade Conta para criação de uma chave secundária
    @OneToMany(mappedBy = "cliente")
    private List<Conta> contas = new ArrayList<>();



    // inicio do métodos getters
    public long getId_cliente() {
        return id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public status getStatus() {
        return status;
    }

    public List<Conta> getContas() {
        return contas;
    }


    // Inicio dos métodos setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }


}
