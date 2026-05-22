package br.com.impacta.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.lab.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
