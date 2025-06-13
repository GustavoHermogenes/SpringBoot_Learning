package com.example.Bradesco.model;

import jakarta.persistence.*;

@Entity
public class ValorRepetido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double valor;
    private int quantidade;
    private String ultimoRegistro;

    @ManyToOne
    private Conta conta;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getUltimoRegistro() {
        return ultimoRegistro;
    }

    public void setUltimoRegistro(String ultimoRegistro) {
        this.ultimoRegistro = ultimoRegistro;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
