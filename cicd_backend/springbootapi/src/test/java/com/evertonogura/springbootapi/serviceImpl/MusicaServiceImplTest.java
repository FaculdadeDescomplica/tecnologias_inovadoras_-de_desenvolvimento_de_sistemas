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
import com.evertonogura.springbootapi.entity.MusicaEntity;
import com.evertonogura.springbootapi.exception.MusicaNotFoundException;
import com.evertonogura.springbootapi.model.MusicaModel;
import com.evertonogura.springbootapi.repository.MusicaRepository;
import com.evertonogura.springbootapi.service.AlbumService;
import com.evertonogura.springbootapi.service.ArtistaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:applicationTest.properties")
public class MusicaServiceImplTest {
		
	@InjectMocks
	private MusicaServiceImpl musicaServiceImpl;

	@Mock
	private MusicaRepository musicaRepository;
	
	@Mock
	private ArtistaService artistaService;
	
	@Mock
	private AlbumService albumService;
	
	private MusicaModel model;
	private MusicaEntity musica;
	private List<MusicaEntity> musicas;
	private MusicaNotFoundException musicaNotFoundException;
	
	@BeforeEach
	public void SetUp() {
		ArtistaEntity artista = new ArtistaEntity(Long.parseLong("1"), "Teste");
		AlbumEntity album = new AlbumEntity(Long.parseLong("1"), "Teste", 1999);
		model = new MusicaModel(Long.parseLong("1"), "Teste", 3.59, album.getId());
		musica = new MusicaEntity(Long.parseLong("1"), "Teste", 3.59);
		musicas = new ArrayList<MusicaEntity>();
		musicas.add(musica);
		musicaNotFoundException = new MusicaNotFoundException(Long.parseLong("1"));
		when(artistaService.validaArtista(anyLong())).thenReturn(artista);
		when(albumService.getAlbum(anyLong())).thenReturn(album);
		when(albumService.validaAlbum(anyLong())).thenReturn(album);
		when(musicaRepository.save(any(MusicaEntity.class))).thenReturn(musica);
		when(musicaRepository.findAll()).thenReturn(musicas);
		when(musicaRepository.findById(anyLong())).thenReturn(Optional.of(musica));
		when(musicaRepository.findByIdAlbum(anyLong())).thenReturn(musicas);
		when(musicaRepository.findByIdAlbumAndNome(anyLong(), anyString())).thenReturn(Optional.of(musica));
		doThrow(musicaNotFoundException).when(musicaRepository).delete(any(MusicaEntity.class));
		when(musicaRepository.findByIdAlbumAndNome(anyLong(), anyString())).thenReturn(Optional.ofNullable(null));
	}
	
	@Test
	public void Add() {
		MusicaEntity musicaResponse = musicaServiceImpl.newMusica(model);
		assertEquals(musicaResponse.getNome(), musica.getNome());
	}
	
	@Test
	public void AddException() {
		when(musicaRepository.findByIdAlbumAndNome(anyLong(), anyString())).thenReturn(Optional.of(musica));
		Exception exception = null;
		
		try {
			musicaServiceImpl.newMusica(model);
		} catch (Exception ex) {
			exception = ex;
		}
		
		assertEquals("Música já existe. Nome: Teste", exception.getMessage());
	}
	
	@Test
	public void Update() {
		MusicaEntity musicaResponse = musicaServiceImpl.updateMusica(Long.parseLong("1"), model);
		assertEquals(musicaResponse.getNome(), musica.getNome());
	}
	
	@Test
	public void GetList() {
		List<MusicaEntity> musicasResponse = musicaServiceImpl.listMusica(Optional.ofNullable(null));
		assertEquals(musicasResponse.get(0).getNome(), musica.getNome());
	}
	
	@Test
	public void GetListFiltered() {
		List<MusicaEntity> musicasResponse = musicaServiceImpl.listMusica(Optional.of("1"));
		assertEquals(musicasResponse.get(0).getNome(), musica.getNome());
	}
	
	@Test
	public void GetItem() {
		MusicaEntity musicaResponse = musicaServiceImpl.getMusica(Long.parseLong("1"));
		assertEquals(musicaResponse.getNome(), musica.getNome());
	}
	
	@Test
	public void Delete() {
		doNothing().when(musicaRepository).delete(any(MusicaEntity.class));
		musicaServiceImpl.deleteMusica(Long.parseLong("1"));
	}
	
	@Test
	public void Valida() {
		when(musicaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		Exception exception = null;
		
		try {
			musicaServiceImpl.validaMusica(Long.parseLong("1"));
		} catch (Exception ex) {
			exception = ex;
		}
		
		assertEquals("Música não encontrada. Id: 1", exception.getMessage());
	}
	
}
