package br.com.impacta.lab.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoUpdateRequest(
		@NotBlank(message = "Nome e obrigatorio")  @Size(min = 2, max = 100, message = "O nome tem que ter entre 2 e 100 caracteres") String nome,
		@NotNull(message = "O preco nao pode ser nulo") @Positive(message = "O preco tem que ser positivo") Double preco,
		@Size(max = 200, message = "A descricao nao pode passar de 200 caracteres") String descricao) {

}
