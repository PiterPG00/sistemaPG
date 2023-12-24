package com.br.piterpg.sistemapg.repositorios;

import com.br.piterpg.sistemapg.modelos.Entrada;
import com.br.piterpg.sistemapg.modelos.ItemEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EntradaRepositorio extends JpaRepository<Entrada, Long> {
}
