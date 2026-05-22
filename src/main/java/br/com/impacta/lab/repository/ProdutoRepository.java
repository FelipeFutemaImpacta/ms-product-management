package br.com.impacta.lab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.impacta.lab.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("""
			SELECT DISTINCT p FROM Produto p
			LEFT JOIN FETCH p.categoria
			LEFT JOIN FETCH p.produtoTags pt
			LEFT JOIN FETCH pt.tag
			""")
	List<Produto> findAllComRelacionamentos();

	@Query("""
			SELECT p FROM Produto p
			LEFT JOIN FETCH p.categoria
			LEFT JOIN FETCH p.produtoTags pt
			LEFT JOIN FETCH pt.tag
			WHERE p.id = :id
			""")
	Optional<Produto> findByIdComRelacionamentos(@Param("id") Long id);


}
