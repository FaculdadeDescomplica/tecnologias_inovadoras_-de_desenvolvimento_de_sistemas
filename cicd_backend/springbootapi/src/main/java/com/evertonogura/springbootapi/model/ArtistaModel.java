package com.evertonogura.springbootapi.model;

import java.io.Serializable;
import java.util.Objects;

public class ArtistaModel implements Serializable {
	private static final long serialVersionUID = -7793083624943535388L;
	
	private Long id;
	
	private String nome;
	
	public ArtistaModel() {}

	public ArtistaModel(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtistaModel other = (ArtistaModel) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "ArtistaModel [id=" + id + ", nome=" + nome + "]";
	}
}
