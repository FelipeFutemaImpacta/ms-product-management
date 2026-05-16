package br.com.impacta.lab.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.impacta.lab.dto.ErrorResponse;

@ControllerAdvice
public class ExceptionHandlerGlobal {


	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> trataCamposInvalidos(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		List<String> erros = new ArrayList<>();
		
		for (var fieldError : fieldErrors) {
			erros.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.badRequest().body(new ErrorResponse(400, "Dados invalidos", erros));
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> trataErroGenerico(NotFoundException exception) {
		return ResponseEntity.status(404).body(new ErrorResponse(404, exception.getMessage(), null));
	}
	
	@ExceptionHandler(ProdutoJaCadastradoException.class)
	public ResponseEntity<ErrorResponse> trataProdutoJaCadastrado(ProdutoJaCadastradoException exception) {
		return ResponseEntity.status(420).body(new ErrorResponse(420, exception.getMessage(), null));
	}
	
	@ExceptionHandler(PrecoInvalidoException.class)
	public ResponseEntity<ErrorResponse> trataPrecoInvalidoException(PrecoInvalidoException exception) {
		return ResponseEntity.status(420).body(new ErrorResponse(420, exception.getMessage(), null));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> trataErroGenerico(Exception exception) {
		List<String> erros = new ArrayList<>();
		
		System.out.println("Mensagem dentro da exception: " + exception.getMessage());
		
		return ResponseEntity.internalServerError().body(new ErrorResponse(500, "Erro interno", erros));
	}
	
}
