package com.evertonogura.springbootapi.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Musica")
public class MusicaEntity implements Serializable {
	private static final long serialVersionUID = -4213481843593437506L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Musica_Gen")
    @SequenceGenerator(name="Musica_Gen", sequenceName="Musica_Seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "Nome_Musica")
	private String nome;
	
	@Column(name = "Duracao")
	private double duracao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Id_Album", nullable = false)
	private AlbumEntity album;
	
	public MusicaEntity() {}
	
	public MusicaEntity(String nome, double duracao, AlbumEntity album) {
		super();
		this.nome = nome;
		this.duracao = duracao;
		this.album = album;
	}
	
	public MusicaEntity(Long id, String nome, double duracao) {
		super();
		this.id = id;
		this.nome = nome;
		this.duracao = duracao;
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
	
	public AlbumEntity getAlbum() {
		return album;
	}
	
	public void setAlbum(AlbumEntity album) {
		this.album = album;
	}

	@Override
	public int hashCode() {
		return Objects.hash(album, duracao, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MusicaEntity other = (MusicaEntity) obj;
		return Objects.equals(album, other.album)
				&& Double.doubleToLongBits(duracao) == Double.doubleToLongBits(other.duracao)
				&& Objects.equals(id, other.id) 
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Musica [id=" + id + ", nome=" + nome + ", duracao=" + duracao + ", album=" + album + "]";
	}
}
