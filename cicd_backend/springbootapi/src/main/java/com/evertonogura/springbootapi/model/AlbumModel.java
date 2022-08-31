package com.evertonogura.springbootapi.model;

import java.io.Serializable;
import java.util.Objects;

public class AlbumModel implements Serializable {
	private static final long serialVersionUID = 6726788487256182191L;
	
	private Long id;
	
	private String nome;
	
	private int ano;
	
	private Long idArtista;
	
	public AlbumModel() {}
	
	public AlbumModel(Long id, String nome, int ano, Long idArtista) {
		super();
		this.id = id;
		this.nome = nome;
		this.ano = ano;
		this.idArtista = idArtista;
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

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Long getIdArtista() {
		return idArtista;
	}

	public void setIdArtista(Long idArtista) {
		this.idArtista = idArtista;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano, id, idArtista, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumModel other = (AlbumModel) obj;
		return ano == other.ano && Objects.equals(id, other.id) && Objects.equals(idArtista, other.idArtista)
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "AlbumModel [id=" + id + ", nome=" + nome + ", ano=" + ano + ", idArtista=" + idArtista + "]";
	}
	
}
