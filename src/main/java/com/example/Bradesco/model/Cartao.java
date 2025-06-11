package com.example.Bradesco.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCartao;

    @Column(name = "numero_cartao", length = 16, nullable = false, unique = true)
    private String numeroCartao;

    @Column(length = 3, nullable = false)
    private String codigoSeguranca; // CVV

    @Column(nullable = false)
    private LocalDate dataValidade;

    @Enumerated(EnumType.STRING)
    private TipoCartao tipoCartao;
    public enum TipoCartao {
        CREDITO, DEBITO, CREDITO_DEBITO
    }

    @Column(precision = 10, scale = 2)
    private BigDecimal limite;

    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status {
        ATIVO, BLOQUEADO, CANCELADO
    }

    @ManyToOne
    @JoinColumn(name = "id_conta", nullable = false)
    private Conta conta; // Relacionamento com Conta (muitos cartões para uma conta)

    // Getters

    public Long getIdCartao() {
        return idCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public TipoCartao getTipoCartao() {
        return tipoCartao;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Status getStatus() {
        return status;
    }

    public Conta getConta() {
        return conta;
    }

    // Setters

    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setTipoCartao(TipoCartao tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}