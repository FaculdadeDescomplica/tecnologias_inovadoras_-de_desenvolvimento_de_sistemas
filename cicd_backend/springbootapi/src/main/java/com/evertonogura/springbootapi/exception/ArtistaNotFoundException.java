package com.evertonogura.springbootapi.exception;

public class ArtistaNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2191881417092520430L;

	public ArtistaNotFoundException(Long id) {
		super("Artista não encontrado. Id: " + id.toString());
	}
}
