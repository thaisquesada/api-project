package br.com.tdd.api.exceptioncontroller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.tdd.api.service.exceptions.DataIntegrityViolationException;
import br.com.tdd.api.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request) {
		StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> DataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
		StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	
	
}