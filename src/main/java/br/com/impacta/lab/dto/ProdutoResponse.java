package br.com.impacta.lab.dto;

import java.util.List;

public record ProdutoResponse(Long id, String nome, Double preco,
		String descricao, CategoriaResponse categoria,
		List<TagResponse> tags) {

}
