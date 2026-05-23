package br.com.impacta.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impacta.lab.entity.ProdutoTagEntity;
import br.com.impacta.lab.entity.ProdutoTagId;


@Repository
public interface ProdutoTagRepository extends JpaRepository<ProdutoTagEntity, ProdutoTagId>{

	
}
