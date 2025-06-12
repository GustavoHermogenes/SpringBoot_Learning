package com.example.Bradesco.model;

import jakarta.persistence.*;

@Entity
public class ContaSuspeita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String motivo;
    private String dataReferencia;
    private double valorEnvolvido;
    private String categoria;

    @ManyToOne
    private ValorRepetido valorRepetido;

    @ManyToOne
    private Conta conta;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(String dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public double getValorEnvolvido() {
        return valorEnvolvido;
    }

    public void setValorEnvolvido(double valorEnvolvido) {
        this.valorEnvolvido = valorEnvolvido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ValorRepetido getValorRepetido() {
        return valorRepetido;
    }

    public void setValorRepetido(ValorRepetido valorRepetido) {
        this.valorRepetido = valorRepetido;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
