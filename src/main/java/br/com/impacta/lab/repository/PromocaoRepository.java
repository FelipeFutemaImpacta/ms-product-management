package br.com.impacta.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impacta.lab.entity.PromocaoEntity;


@Repository
public interface PromocaoRepository extends JpaRepository<PromocaoEntity, Long>{

	
}
