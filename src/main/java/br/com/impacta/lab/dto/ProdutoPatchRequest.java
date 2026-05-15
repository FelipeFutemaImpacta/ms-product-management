package br.com.impacta.lab.dto;

import java.util.Optional;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoPatchRequest(
		Optional<@NotBlank @Size(min = 2, max = 100) String> nome,
		Optional<@Size(max = 255) String> descricao,
		Optional<@Positive Double> preco) {

}
