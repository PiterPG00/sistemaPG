package com.br.piterpg.sistemapg.repositorios;

import com.br.piterpg.sistemapg.modelos.Contrato;
import com.br.piterpg.sistemapg.modelos.ItemEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContratoRepositorio extends JpaRepository<Contrato, Long> {

}
