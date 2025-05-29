package com.example.Bradesco.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contas")
public class Conta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_conta;

    @Column(length = 8)
    private Integer numero_conta;

    @Column(length = 5)
    private Integer agencia;

    @Column(length = 20)
    private String tipo_conta;

    private LocalDate data_abertura;

    @Column(precision = 10, scale = 2)
    private BigDecimal saldo;

    @Column(length = 25)
    private String senha;

    @Enumerated(EnumType.STRING)
    private status status;
    public enum status {
        ATIVA, INATIVA
    }

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;


    // Inicio dos métodos getters

    public Long getId_conta() {
        return id_conta;
    }

    public Integer getNumero_conta() {
        return numero_conta;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public String getTipo_conta() {
        return tipo_conta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public LocalDate getData_abertura() {
        return data_abertura;
    }

    public String getSenha() {
        return senha;
    }

    public status getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    // inicio do métodos setters

    public void setNumero_conta(Integer numero_conta) {
        this.numero_conta = numero_conta;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public void setTipo_conta(String tipo_conta) {
        this.tipo_conta = tipo_conta;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setData_abertura(LocalDate data_abertura) {
        this.data_abertura = data_abertura;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
