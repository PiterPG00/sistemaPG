package com.br.piterpg.sistemapg.repositorios;

import com.br.piterpg.sistemapg.modelos.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

}
