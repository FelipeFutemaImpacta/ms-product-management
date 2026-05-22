package br.com.impacta.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.lab.entity.Promocao;

public interface PromocaoRepository extends JpaRepository<Promocao, Long> {

}
