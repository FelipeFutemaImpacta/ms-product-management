package br.com.impacta.lab.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoUpdateRequest(
		@NotBlank @Size(min = 2, max = 100) String nome,
		@Size(max = 255) String descricao,
		@NotNull @Positive Double preco) {

}
