package com.evertonogura.springbootapi.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Artista")
public class ArtistaEntity implements Serializable {
	private static final long serialVersionUID = 2533496641167524981L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Artista_Gen")
    @SequenceGenerator(name="Artista_Gen", sequenceName="Artista_Seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "Nome_Artista")
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "artista", fetch = FetchType.LAZY)
	private List<AlbumEntity> albuns;
	
	public ArtistaEntity() {}
	
	public ArtistaEntity(String nome) {
		super();
		this.nome = nome;
	}
	
	public ArtistaEntity(Long id, String nome) {
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
	
	public List<AlbumEntity> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(List<AlbumEntity> albuns) {
		this.albuns = albuns;
	}

	@Override
	public int hashCode() {
		return Objects.hash(albuns, id, nome);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtistaEntity other = (ArtistaEntity) obj;
		return Objects.equals(albuns, other.albuns) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}
	
	@Override
	public String toString() {
		return "Artista [id=" + id + ", nome=" + nome + ", albuns=" + albuns + "]";
	}
}
