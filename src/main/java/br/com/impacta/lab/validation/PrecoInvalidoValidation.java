package br.com.impacta.lab.validation;

import org.springframework.stereotype.Component;

import br.com.impacta.lab.dto.ProdutoRequest;
import br.com.impacta.lab.exception.PrecoInvalidoException;

@Component
public class PrecoInvalidoValidation implements BusinessValdation{

	
	@Override
	public void validar(ProdutoRequest request) {
		
		System.out.println("Dentro do PrecoInvalidoValidation");
		
		if (request.preco() > 10000) {
			throw new PrecoInvalidoException(request.nome(), request.preco());
		}
		
	}

}
