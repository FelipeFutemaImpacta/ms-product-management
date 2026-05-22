package br.com.impacta.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.lab.entity.ProdutoTag;
import br.com.impacta.lab.entity.ProdutoTagId;

public interface ProdutoTagRepository extends JpaRepository<ProdutoTag, ProdutoTagId> {

}
