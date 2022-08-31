package com.evertonogura.springbootapi.serviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.evertonogura.springbootapi.entity.AlbumEntity;
import com.evertonogura.springbootapi.entity.ArtistaEntity;
import com.evertonogura.springbootapi.exception.AlbumNotFoundException;
import com.evertonogura.springbootapi.model.AlbumModel;
import com.evertonogura.springbootapi.repository.AlbumRepository;
import com.evertonogura.springbootapi.service.ArtistaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:applicationTest.properties")
public class AlbumServiceImplTest {
		
	@InjectMocks
	private AlbumServiceImpl albumServiceImpl;

	@Mock
	private AlbumRepository albumRepository;
	
	@Mock
	private ArtistaService artistaService;
	
	private AlbumModel model;
	private AlbumEntity album;
	private List<AlbumEntity> albuns;
	private AlbumNotFoundException albumNotFoundException;
	
	@BeforeEach
	public void SetUp() {
		ArtistaEntity artista = new ArtistaEntity(Long.parseLong("1"), "Teste");
		model = new AlbumModel(Long.parseLong("1"), "Teste", 1999, artista.getId());
		album = new AlbumEntity(model.getId(), model.getNome(), model.getAno());
		albuns = new ArrayList<AlbumEntity>();
		albuns.add(album);
		albumNotFoundException = new AlbumNotFoundException(Long.parseLong("1"));
		when(artistaService.getArtista(anyLong())).thenReturn(artista);
		when(artistaService.validaArtista(anyLong())).thenReturn(artista);
		when(albumRepository.save(any(AlbumEntity.class))).thenReturn(album);
		when(albumRepository.findAll()).thenReturn(albuns);
		when(albumRepository.findById(anyLong())).thenReturn(Optional.of(album));
		when(albumRepository.findByArtista(anyLong())).thenReturn(albuns);
		when(albumRepository.findByIdArtistaAndNome(anyLong(), anyString())).thenReturn(Optional.of(album));
		doThrow(albumNotFoundException).when(albumRepository).delete(any(AlbumEntity.class));
		when(albumRepository.findByIdArtistaAndNome(anyLong(), anyString())).thenReturn(Optional.ofNullable(null));
	}
	
	@Test
	public void Add() {
		AlbumEntity albumResponse = albumServiceImpl.newAlbum(model);
		assertEquals(albumResponse.getNome(), album.getNome());
	}
	
	@Test
	public void AddException() {
		when(albumRepository.findByIdArtistaAndNome(anyLong(), anyString())).thenReturn(Optional.of(album));
		Exception exception = null;
		
		try {
			albumServiceImpl.newAlbum(model);
		} catch (Exception ex) {
			exception = ex;
		}
		
		assertEquals("Album já existe. Nome: Teste", exception.getMessage());
	}
	
	@Test
	public void Update() {
		AlbumEntity albumResponse = albumServiceImpl.updateAlbum(Long.parseLong("1"), model);
		assertEquals(albumResponse.getNome(), album.getNome());
	}
	
	@Test
	public void GetList() {
		List<AlbumEntity> albunsResponse = albumServiceImpl.listAlbum(Optional.ofNullable(null));
		assertEquals(albunsResponse.get(0).getNome(), album.getNome());
	}
	
	@Test
	public void GetListFiltered() {
		List<AlbumEntity> albunsResponse = albumServiceImpl.listAlbum(Optional.of("1"));
		assertEquals(albunsResponse.get(0).getNome(), album.getNome());
	}
	
	@Test
	public void GetItem() {
		AlbumEntity albumResponse = albumServiceImpl.getAlbum(Long.parseLong("1"));
		assertEquals(albumResponse.getNome(), album.getNome());
	}
	
	@Test
	public void Delete() {
		doNothing().when(albumRepository).delete(any(AlbumEntity.class));
		albumServiceImpl.deleteAlbum(Long.parseLong("1"));
	}
	
	@Test
	public void Valida() {
		when(albumRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		Exception exception = null;
		
		try {
			albumServiceImpl.validaAlbum(Long.parseLong("1"));
		} catch (Exception ex) {
			exception = ex;
		}
		
		assertEquals("Album não encontrado. Id: 1", exception.getMessage());
	}
	
}
