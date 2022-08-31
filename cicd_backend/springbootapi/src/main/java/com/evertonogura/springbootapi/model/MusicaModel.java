package com.evertonogura.springbootapi.model;

import java.io.Serializable;
import java.util.Objects;

public class MusicaModel implements Serializable {
	private static final long serialVersionUID = -6535302330131000333L;
	
	private Long id;
	
	private String nome;
	
	private double duracao;
	
	private Long idAlbum;
	
	public MusicaModel() {}

	public MusicaModel(Long id, String nome, double duracao, Long idAlbum) {
		super();
		this.id = id;
		this.nome = nome;
		this.duracao = duracao;
		this.idAlbum = idAlbum;
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

	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

	public Long getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duracao, id, idAlbum, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MusicaModel other = (MusicaModel) obj;
		return Double.doubleToLongBits(duracao) == Double.doubleToLongBits(other.duracao)
				&& Objects.equals(id, other.id) && Objects.equals(idAlbum, other.idAlbum)
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "MusicaModel [id=" + id + ", nome=" + nome + ", duracao=" + duracao + ", idAlbum=" + idAlbum + "]";
	}
}
