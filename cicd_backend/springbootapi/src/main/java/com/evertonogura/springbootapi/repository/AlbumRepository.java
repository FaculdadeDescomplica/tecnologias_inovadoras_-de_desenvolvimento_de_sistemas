package com.evertonogura.springbootapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evertonogura.springbootapi.entity.AlbumEntity;

public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {
	@Query(value = "SELECT Id, Nome_Album, Ano, Id_Artista FROM Album WHERE Id_Artista = :Id_Artista",
			nativeQuery = true)
	List<AlbumEntity> findByArtista(@Param("Id_Artista") Long idArtista);
	
	@Query(value = "SELECT Id, Nome_Album, Ano, Id_Artista FROM Album WHERE Id_Artista = :Id_Artista AND Nome_Album = :Nome",
			nativeQuery = true)
	Optional<AlbumEntity> findByIdArtistaAndNome(@Param("Id_Artista") Long idArtista, @Param("Nome") String nome);
}
