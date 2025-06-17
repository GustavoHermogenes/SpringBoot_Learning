package com.example.Bradesco.controller;

import java.math.BigDecimal;

public class DadosPix {
    private BigDecimal valor;
    private String chavePix;
    private String descricao;

    public DadosPix(BigDecimal valor, String chavePix, String descricao) {
        this.valor = valor;
        this.chavePix = chavePix;
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getChavePix() {
        return chavePix;
    }

    public String getDescricao() {
        return descricao;
    }
}