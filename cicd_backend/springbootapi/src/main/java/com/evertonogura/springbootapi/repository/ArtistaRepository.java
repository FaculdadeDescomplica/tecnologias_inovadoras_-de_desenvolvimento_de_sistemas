package com.evertonogura.springbootapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evertonogura.springbootapi.entity.ArtistaEntity;

public interface ArtistaRepository extends JpaRepository<ArtistaEntity, Long> {
	Optional<ArtistaEntity> findByNome(String nome);
}
