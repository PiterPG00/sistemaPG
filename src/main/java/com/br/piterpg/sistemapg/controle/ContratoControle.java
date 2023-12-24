package com.br.piterpg.sistemapg.controle;

import com.br.piterpg.sistemapg.modelos.Contrato;
import com.br.piterpg.sistemapg.modelos.Entrada;
import com.br.piterpg.sistemapg.repositorios.ContratoRepositorio;
import com.br.piterpg.sistemapg.repositorios.EntradaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class ContratoControle {
    @Autowired
    private ContratoRepositorio contratoRepositorio;

    @Autowired
    private EntradaRepositorio entradaRepositorio;

    @GetMapping("/cadastroContrato")
    public ModelAndView cadastrar(Contrato contrato) {
        ModelAndView mv = new ModelAndView("administrativo/contratos/cadastro");
        mv.addObject("contrato", contrato);
        return mv;
    }

    @GetMapping("/visualizarContrato2")
    public ModelAndView visualizar2(Contrato contrato) {
        ModelAndView mv = new ModelAndView("administrativo/contratos/visualizar");
        mv.addObject("contrato", contrato);
        return mv;
    }


    @GetMapping("/listaContrato")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/contratos/lista");
        mv.addObject("listaContratos", contratoRepositorio.findAll());
        return mv;
    }


    @GetMapping("/visualizarContrato/{id}")
    public ModelAndView visualizar(@PathVariable("id") Long id) {
        Optional<Contrato> contrato = contratoRepositorio.findById(id);
        return visualizar2(contrato.get());
    }

    @GetMapping("/editarContrato/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Contrato> contrato = contratoRepositorio.findById(id);
        return cadastrar(contrato.get());
    }

    @GetMapping("/removerContrato/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, Entrada entrada) {
        Optional<Contrato> contrato = contratoRepositorio.findById(id);

        if (contrato.isPresent()) {
            List<Entrada> entradas = entradaRepositorio.findAll();

            entradas.stream()
                    .filter(e -> e.getContrato().getId().equals(id))
                    .forEach(entradaRepositorio::delete);

            contratoRepositorio.delete(contrato.get());
        }

        return listar();
    }

    @PostMapping("/salvarContrato")
    public ModelAndView salvar(Contrato contrato, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(contrato);
        }
        contratoRepositorio.saveAndFlush(contrato);
        return cadastrar(new Contrato());
    }

}
