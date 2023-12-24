package com.br.piterpg.sistemapg.modelos;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Entity
@Table(name="contrato")
public class Contrato implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeContrato;
    private String observacao;
    private String produto;
    private String valorFinal;
    private String valorAtual;
    private String dataInicial;
    private String dataFinal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeContrato() {
        return nomeContrato;
    }

    public void setNomeContrato(String nomeContrato) {
        this.nomeContrato = nomeContrato;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(String valorFinal) {
        this.valorFinal = valorFinal;
    }

    public String getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(String valorAtual) {
        this.valorAtual = valorAtual;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String valorAtual2(){
        return "R$ " + valorAtual;
    }

    public String valorFinal2(){
        return "R$ " + valorFinal;
    }

    public String calcularRestante(){
        String valorFinal = this.getValorFinal().replaceAll(",", "").replaceAll("\\.","");
        String valorAtual = this.getValorAtual().replaceAll(",", "").replaceAll("\\.","");
        double calcularRestante = Double.parseDouble(valorFinal) - Double.parseDouble(valorAtual);
        calcularRestante = calcularRestante / 100;
        if(calcularRestante < 0){
            calcularRestante = 0;
        }else {
            DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("pt", "Brazil"));
            dfs.setDecimalSeparator(',');
            dfs.setGroupingSeparator('.');

            DecimalFormat df = new DecimalFormat("#,##0.00", dfs);
            return "R$ " + df.format(calcularRestante);
        }
        return "R$ " + String.valueOf(calcularRestante);
    }

}