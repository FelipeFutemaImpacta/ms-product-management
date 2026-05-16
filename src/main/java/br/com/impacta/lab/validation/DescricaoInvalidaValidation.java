package br.com.impacta.lab.validation;

import org.springframework.stereotype.Component;

import br.com.impacta.lab.dto.ProdutoRequest;

@Component
public class DescricaoInvalidaValidation implements BusinessValdation{

	@Override
	public void validar(ProdutoRequest request) {
	
		if ("string".equals(request.descricao())) {
			throw new RuntimeException("Descricao invalida");
		}
		
		
	}

}
