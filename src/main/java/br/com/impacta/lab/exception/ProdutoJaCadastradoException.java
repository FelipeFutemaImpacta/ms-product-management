package br.com.impacta.lab.exception;

public class ProdutoJaCadastradoException extends RuntimeException {

	public ProdutoJaCadastradoException(String nome) {
		super("O produto de nome: " + nome + " ja foi cadastrado");
	}
	
}
