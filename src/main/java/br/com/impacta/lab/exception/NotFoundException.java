package br.com.impacta.lab.exception;

public class NotFoundException extends RuntimeException {

	public NotFoundException(Long id) {
		super("O produto de id: " + id + " nao foi encontrado");
	}
	
}
