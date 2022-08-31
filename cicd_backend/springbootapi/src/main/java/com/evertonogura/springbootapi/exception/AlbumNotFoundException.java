package com.evertonogura.springbootapi.exception;

public class AlbumNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -8194197155981472881L;

	public AlbumNotFoundException(Long id) {
		super("Album não encontrado. Id: " + id.toString());
	}
}
