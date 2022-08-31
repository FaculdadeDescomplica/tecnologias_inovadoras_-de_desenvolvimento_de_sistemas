package com.evertonogura.springbootapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.evertonogura.springbootapi.entity.AlbumEntity;
import com.evertonogura.springbootapi.model.AlbumModel;

@Component
public interface AlbumService {
	AlbumEntity newAlbum(AlbumModel newAlbum);
	
	AlbumEntity updateAlbum(Long id, AlbumModel newAlbum);
	
	List<AlbumEntity> listAlbum(Optional<String> idArtista);
	
	AlbumEntity getAlbum(Long id);
	
	void deleteAlbum(Long id);
	
	AlbumEntity validaAlbum(Long id);
}
