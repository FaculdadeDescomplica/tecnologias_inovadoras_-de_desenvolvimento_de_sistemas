package com.evertonogura.springbootapi.exception;

public class AlbumAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = -859933568690727809L;
	
	public AlbumAlreadyExistsException(String nome) {
		super("Album já existe. Nome: " + nome);
	}
}
