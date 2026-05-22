package br.com.impacta.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.impacta.lab.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
