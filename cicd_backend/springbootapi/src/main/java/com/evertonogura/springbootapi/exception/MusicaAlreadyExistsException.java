package com.evertonogura.springbootapi.exception;

public class MusicaAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 6621261799250923461L;
	
	public MusicaAlreadyExistsException(String nome) {
		super("Música já existe. Nome: " + nome);
	}
}
