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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Album")
public class AlbumEntity implements Serializable {
	private static final long serialVersionUID = -8255679608724177448L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Album_Gen")
    @SequenceGenerator(name="Album_Gen", sequenceName="Album_Seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "Nome_Album")
	private String nome;
	
	@Column(name = "Ano")
	private int ano;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Id_Artista", nullable = false)
	private ArtistaEntity artista;
	
	@JsonIgnore
	@OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
	private List<MusicaEntity> musicas;
	
	public AlbumEntity() {}
	
	public AlbumEntity(String nome, int ano, ArtistaEntity artista) {
		super();
		this.nome = nome;
		this.ano = ano;
		this.artista = artista;
	}
	
	public AlbumEntity(Long id, String nome, int ano) {
		super();
		this.id = id;
		this.nome = nome;
		this.ano = ano;
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
	
	public ArtistaEntity getArtista() {
		return artista;
	}
	
	public void setArtista(ArtistaEntity artista) {
		this.artista = artista;
	}

	public List<MusicaEntity> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<MusicaEntity> musicas) {
		this.musicas = musicas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano, artista, id, musicas, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumEntity other = (AlbumEntity) obj;
		return ano == other.ano && Objects.equals(artista, other.artista) && Objects.equals(id, other.id)
				&& Objects.equals(musicas, other.musicas) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", nome=" + nome + ", ano=" + ano + ", artista=" + artista + ", musicas=" + musicas
				+ "]";
	}
	
}
