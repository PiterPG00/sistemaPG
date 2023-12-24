package com.br.piterpg.sistemapg.controle;

import com.br.piterpg.sistemapg.modelos.Contrato;
import com.br.piterpg.sistemapg.repositorios.ContratoRepositorio;
import com.br.piterpg.sistemapg.repositorios.EntradaRepositorio;
import com.br.piterpg.sistemapg.modelos.Entrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class EntradaControle {

    @Autowired
    private EntradaRepositorio entradaRepositorio;

    @Autowired
    private ContratoRepositorio contratoRepositorio;

    private List<Entrada> entrada1 = new ArrayList<Entrada>();

    @GetMapping("/cadastroEntrada")
    public ModelAndView cadastrar(Entrada entrada) {
        ModelAndView mv = new ModelAndView("administrativo/entradas/cadastro");
        mv.addObject("entrada", entrada);
        mv.addObject("listaContratos", contratoRepositorio.findAll());
        return mv;
    }


    @GetMapping("/listaEntrada")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/entradas/lista");
        mv.addObject("listaEntradas", entradaRepositorio.findAll());
        return mv;
    }


/*
    @GetMapping("/editarEntrada/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Entrada> entrada = entradaRepositorio.findById(id);
        this.entrada1 = itemEntradaRepositorio.buscarPorEntrada(entrada.get().getId());

        return cadastrar(entrada.get(), new ItemEntrada());
    }

 */

    @GetMapping("/removerEntrada/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, Entrada entrada) {

        Optional<Entrada> entrada1 = entradaRepositorio.findById(entrada.getId());
        Entrada entrada2 = entrada1.get();

        Optional<Contrato> contr = contratoRepositorio.findById(entrada2.getContrato().getId());
        Contrato contrato = contr.get();

        String valorAtual = contrato.getValorAtual().replaceAll(",", "")
                .replaceAll("\\.","");

        String valorSaida = entrada2.getValorEntrada().replaceAll(",", "")
                .replaceAll("\\.","");

        double calcular = Double.parseDouble(valorAtual) - Double.parseDouble(valorSaida);
        calcular = calcular / 100;
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("pt", "Brazil"));
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');

        DecimalFormat df = new DecimalFormat("#,##0.00", dfs);

        contrato.setValorAtual(df.format(calcular));
        contratoRepositorio.saveAndFlush(contrato);
        entradaRepositorio.delete(entrada);

        return listar();
    }


    @PostMapping("/salvarEntrada")
    public ModelAndView salvar(String acao, Entrada entrada, BindingResult result) {
        if(result.hasErrors()) {
            return cadastrar(entrada);

        }
        if(acao.equals("salvar")){

            Optional<Contrato> contr = contratoRepositorio.findById(entrada.getContrato().getId());

            Contrato contrato = contr.get();

            String valorAtual = contrato.getValorAtual().replaceAll(",", "")
                    .replaceAll("\\.","");

            String valorEntrada = entrada.getValorEntrada().replaceAll(",", "")
                    .replaceAll("\\.","");

            double calcular = Double.parseDouble(valorAtual) + Double.parseDouble(valorEntrada);
            calcular = calcular / 100;
            DecimalFormatSymbols dfs = new DecimalFormatSymbols(new Locale("pt", "Brazil"));
            dfs.setDecimalSeparator(',');
            dfs.setGroupingSeparator('.');

            DecimalFormat df = new DecimalFormat("#,##0.00", dfs);

            contrato.setValorAtual(df.format(calcular));


            entrada.setNomeContrato(contrato.getNomeContrato());

            entradaRepositorio.saveAndFlush(entrada);
//            contratoRepositorio.saveAndFlush(contrato);
            return cadastrar(new Entrada());
        }
        return cadastrar(entrada);
    }

}
