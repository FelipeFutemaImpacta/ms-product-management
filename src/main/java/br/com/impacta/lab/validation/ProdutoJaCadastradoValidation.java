package br.com.impacta.lab.validation;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.impacta.lab.dto.ProdutoRequest;
import br.com.impacta.lab.exception.ProdutoJaCadastradoException;
import br.com.impacta.lab.entity.Produto;
import br.com.impacta.lab.repository.ProdutoRepository;

@Component
public class ProdutoJaCadastradoValidation implements BusinessValdation {

	private final ProdutoRepository repository;

	public ProdutoJaCadastradoValidation(ProdutoRepository repository) {
		this.repository = repository;
	}

	@Override
	public void validar(ProdutoRequest request) {
		List<Produto> produtos = repository.findAll();

		for (var produto : produtos) {
			if (request.nome().equals(produto.getNome())) {
				throw new ProdutoJaCadastradoException(request.nome());
			}
		}
	}

}
