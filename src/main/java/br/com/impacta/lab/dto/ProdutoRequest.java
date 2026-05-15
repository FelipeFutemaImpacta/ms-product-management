package br.com.impacta.lab.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoRequest(
		@NotBlank(message = "Nome é obrigatório") @Size(min = 2, max = 100) String nome,
		@NotNull(message = "Preço é obrigatório") @Positive(message = "Preço deve ser maior que zero") Double preco,
		@Size(max = 255) String descricao) {

}
