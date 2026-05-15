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
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<String> erros = new ArrayList<>();

		for (FieldError fieldError : fieldErrors) {
			String mensagem = fieldError.getField() + ": " + fieldError.getDefaultMessage();
			erros.add(mensagem);
		}

		ErrorResponse body = new ErrorResponse(400, "Dados invalidos", erros);
		return ResponseEntity.status(400).body(body);
	}

	@ExceptionHandler(ProdutoNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(ProdutoNotFoundException ex) {
		ErrorResponse body = new ErrorResponse(404, ex.getMessage());
		return ResponseEntity.status(404).body(body);
	}

}
