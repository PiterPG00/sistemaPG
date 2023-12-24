package com.br.piterpg.sistemapg.modelos;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class ItemEntrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double valorEntrada = 0.00;


    @ManyToOne
    private Entrada entrada;

    @ManyToOne
    private Contrato contrato;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(Double valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public static long getSerialVersionUID(){
        return serialVersionUID;
    }
}