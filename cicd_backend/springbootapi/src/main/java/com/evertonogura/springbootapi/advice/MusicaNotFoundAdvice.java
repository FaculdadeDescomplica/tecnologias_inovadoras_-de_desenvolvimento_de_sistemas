package com.evertonogura.springbootapi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.evertonogura.springbootapi.exception.MusicaNotFoundException;
import com.evertonogura.springbootapi.model.ErroModel;

@ControllerAdvice
public class MusicaNotFoundAdvice {
	@ExceptionHandler(MusicaNotFoundException.class)
	public ResponseEntity<ErroModel> musicaNotFoundHandler(MusicaNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroModel(ex.getMessage()));
	}
}
