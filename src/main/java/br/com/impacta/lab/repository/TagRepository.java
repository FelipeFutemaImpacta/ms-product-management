package br.com.impacta.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.impacta.lab.entity.TagEntity;


@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long>{

	
}
