package com.evertonogura.springbootapi.exception;

public class MusicaNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7484001427128746559L;

	public MusicaNotFoundException(Long id) {
		super("Música não encontrada. Id: " + id.toString());
	}
}
