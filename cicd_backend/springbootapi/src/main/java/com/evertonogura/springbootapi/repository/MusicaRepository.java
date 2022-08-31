package com.evertonogura.springbootapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evertonogura.springbootapi.entity.MusicaEntity;

public interface MusicaRepository extends JpaRepository<MusicaEntity, Long> {
	@Query(value = "SELECT Id, Nome_Musica, Duracao, Id_Album FROM Musica WHERE Id_Album = :Id_Album",
			nativeQuery = true)
	List<MusicaEntity> findByIdAlbum(@Param("Id_Album") Long idAlbum);
	
	@Query(value = "SELECT Id, Nome_Musica, Duracao, Id_Album FROM Musica WHERE Id_Album = :Id_Album AND Nome_Musica = :Nome_Musica",
			nativeQuery = true)
	Optional<MusicaEntity> findByIdAlbumAndNome(@Param("Id_Album") Long idAlbum, @Param("Nome_Musica") String nome);
}
