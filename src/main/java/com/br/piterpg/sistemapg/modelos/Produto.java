package com.br.piterpg.sistemapg.modelos;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Entity
@Table(name="produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Double quantidade = 0.00;
    private String precoVenda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(String precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String quantidade2(){
        return quantidade + " kg";
    }

    public String precoVenda2(){
        return "R$ " + precoVenda;
    }

    public String precoTotal() {
        String valor = this.getPrecoVenda().replaceAll(",", "").replaceAll("\\.","");
        double calcular = Double.parseDouble(valor) * quantidade;
        calcular = calcular / 100;

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("pt", "Brazil"));
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');

        DecimalFormat df = new DecimalFormat("#,##0.00", dfs);
        return "R$ " + df.format(calcular);
    }
}