package com.evertonogura.springbootapi.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.evertonogura.springbootapi.entity.ArtistaEntity;
import com.evertonogura.springbootapi.model.ArtistaModel;

@Component
public interface ArtistaService {
	ArtistaEntity newArtista(ArtistaModel newArtista);
	
	ArtistaEntity updateArtista(Long id, ArtistaModel newArtista);
	
	List<ArtistaEntity> listArtista();
	
	ArtistaEntity getArtista(Long id);
	
	void deleteArtista(Long id);
	
	ArtistaEntity validaArtista(Long id);
}
