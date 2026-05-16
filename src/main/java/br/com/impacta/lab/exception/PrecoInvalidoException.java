package br.com.impacta.lab.exception;

public class PrecoInvalidoException extends RuntimeException {

	public PrecoInvalidoException(String nome, Double preco) {
		super("O produto de " + nome + " nao pode ser cadastrado com o valor de : " + preco);
	}
	
}
