package com.evertonogura.springbootapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.evertonogura.springbootapi.exception.AlbumAlreadyExistsException;
import com.evertonogura.springbootapi.model.ErroModel;

@ControllerAdvice
public class AlbumAlreadyExistsAdvice extends ResponseEntityExceptionHandler {
	@ExceptionHandler(AlbumAlreadyExistsException.class)
	public ResponseEntity<ErroModel> albumAlreadyExistsHandler(AlbumAlreadyExistsException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroModel(ex.getMessage()));
	}
}
