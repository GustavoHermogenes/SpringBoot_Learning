package com.example.Bradesco.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @Enumerated(EnumType.STRING)
    private tipo_conta tipo_conta;
    public enum  tipo_conta{
        CORRENTE, POUPANCA;
    }

    private LocalDate data_abertura;

    @Column(precision = 10, scale = 2)
    private BigDecimal saldo;

    @Column(length = 25)
    private String senha;

    @Column(unique = true)
    private String chavePix;


    @Enumerated(EnumType.STRING)
    private status status;
    public enum status {
        ATIVA, INATIVA
    }

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "conta")
    private List<Cartao> cartoes = new ArrayList<>();

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

    public tipo_conta getTipo_conta() {
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

    public String getChavePix() {
    return chavePix;
}

    public status getStatus() {
        return status;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public List<Cartao> getCartoes() {
    return cartoes;
}


    // inicio do métodos setters

    
    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public void setNumero_conta(Integer numero_conta) {
        this.numero_conta = numero_conta;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public void setTipo_conta(tipo_conta tipo_conta) {
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
    
public void setChavePix(String chavePix) {
    this.chavePix = chavePix;
}

    public void setStatus(status status) {
        this.status = status;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
