package br.com.impacta.lab.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.impacta.lab.dto.ProdutoRequest;
import br.com.impacta.lab.entity.ProdutoEntity;
import br.com.impacta.lab.exception.ProdutoJaCadastradoException;
import br.com.impacta.lab.repository.ProdutoRepository;

@Component
public class ProdutoJaCadastradoValidation implements BusinessValdation {

	@Autowired
	private ProdutoRepository repository;
	
	@Override
	public void validar(ProdutoRequest request) {
		
		System.out.println("Dentro do ProdutoJaCadastradoValidation");
		
		List<ProdutoEntity> listarTodos = repository.listarTodos();
		
		for (var produto :  listarTodos) {
			if (request.nome().equals(produto.getNome())) {
				throw new ProdutoJaCadastradoException(request.nome());
			}
		}
		
	}

}
