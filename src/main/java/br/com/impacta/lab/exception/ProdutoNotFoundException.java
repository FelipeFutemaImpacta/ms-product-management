package br.com.impacta.lab.exception;

public class ProdutoNotFoundException extends RuntimeException {

	public ProdutoNotFoundException(Long id) {
		super("Produto não encontrado com id: " + id);
	}

}
