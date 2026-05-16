package br.com.impacta.lab.dto;

import java.util.Optional;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoPatchRequest(
		Optional<@Size(min = 2, max = 100, message = "O nome tem que ter entre 2 e 100 caracteres") String> nome,
		Optional<@Positive(message = "O preco tem que ser positivo") Double> preco,
		Optional<@Size(max = 200, message = "A descricao nao pode passar de 200 caracteres") String> descricao) {

}
