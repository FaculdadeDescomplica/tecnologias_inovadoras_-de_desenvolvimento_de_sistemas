package com.evertonogura.springbootapi.exception;

public class ArtistaAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -1078732418380696153L;

	public ArtistaAlreadyExistsException(String nome) {
		super("Artista já existe. Nome: " + nome);
	}
}
