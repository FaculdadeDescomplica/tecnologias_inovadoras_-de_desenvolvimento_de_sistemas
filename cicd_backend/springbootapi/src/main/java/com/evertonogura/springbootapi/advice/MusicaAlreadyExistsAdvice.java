package com.evertonogura.springbootapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.evertonogura.springbootapi.exception.MusicaAlreadyExistsException;
import com.evertonogura.springbootapi.model.ErroModel;

@ControllerAdvice
public class MusicaAlreadyExistsAdvice {
	@ExceptionHandler(MusicaAlreadyExistsException.class)
	public ResponseEntity<ErroModel> musicaAlreadyExistsHandler(MusicaAlreadyExistsException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroModel(ex.getMessage()));
	}
}
