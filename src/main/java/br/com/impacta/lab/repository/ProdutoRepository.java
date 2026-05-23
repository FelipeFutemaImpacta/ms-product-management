package br.com.impacta.lab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.impacta.lab.entity.ProdutoEntity;


@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>{

	public List<ProdutoEntity> findByNomeAndDescricao(String nome, String descricao);
	
	@Query(value = "select * from produtos p where descricao like :descricao", nativeQuery = true)
	public List<ProdutoEntity> buscaPorDescricao(@Param("descricao") String descricao);
	
	@Query(""" 
			select p from ProdutoEntity p 
			join fetch p.categoria 
			left join fetch p.produtoTags pt
			left join fetch pt.tag
			where p.id = :id 
			""")
	public Optional<ProdutoEntity> findByIdFull(@Param("id") Long id);
	
}
