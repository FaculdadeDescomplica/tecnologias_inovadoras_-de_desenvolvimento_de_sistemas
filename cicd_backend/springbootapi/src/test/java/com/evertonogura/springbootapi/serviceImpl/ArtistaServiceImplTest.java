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

import com.evertonogura.springbootapi.entity.ArtistaEntity;
import com.evertonogura.springbootapi.exception.ArtistaNotFoundException;
import com.evertonogura.springbootapi.model.ArtistaModel;
import com.evertonogura.springbootapi.repository.ArtistaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:applicationTest.properties")
public class ArtistaServiceImplTest {
		
	@InjectMocks
	private ArtistaServiceImpl artistaServiceImpl;

	@Mock
	private ArtistaRepository artistaRepository;
	
	private ArtistaModel model;
	private ArtistaEntity artista;
	private List<ArtistaEntity> artistas;
	private ArtistaNotFoundException artistaNotFoundException;
	
	@BeforeEach
	public void SetUp() {
		model = new ArtistaModel(Long.parseLong("1"), "Teste");
		artista = new ArtistaEntity(model.getId(), model.getNome());
		artistas = new ArrayList<ArtistaEntity>();
		artistas.add(artista);
		artistaNotFoundException = new ArtistaNotFoundException(Long.parseLong("1"));
		when(artistaRepository.save(any(ArtistaEntity.class))).thenReturn(artista);
		when(artistaRepository.findAll()).thenReturn(artistas);
		when(artistaRepository.findById(anyLong())).thenReturn(Optional.of(artista));
		when(artistaRepository.findByNome(anyString())).thenReturn(Optional.of(artista));
		doThrow(artistaNotFoundException).when(artistaRepository).delete(any(ArtistaEntity.class));
		when(artistaRepository.findByNome(anyString())).thenReturn(Optional.ofNullable(null));
	}
	
	@Test
	public void Add() {
		ArtistaEntity artistaResponse = artistaServiceImpl.newArtista(model);
		assertEquals(artistaResponse.getNome(), artista.getNome());
	}
	
	@Test
	public void AddException() {
		when(artistaRepository.findByNome(anyString())).thenReturn(Optional.of(artista));
		Exception exception = null;
		
		try {
			artistaServiceImpl.newArtista(model);
		} catch (Exception ex) {
			exception = ex;
		}
		
		assertEquals("Artista já existe. Nome: Teste", exception.getMessage());
	}
	
	@Test
	public void Update() {
		ArtistaEntity artistaResponse = artistaServiceImpl.updateArtista(Long.parseLong("1"), model);
		assertEquals(artistaResponse.getNome(), artista.getNome());
	}
	
	@Test
	public void GetList() {
		List<ArtistaEntity> artistasResponse = artistaServiceImpl.listArtista();
		assertEquals(artistasResponse.get(0).getNome(), artista.getNome());
	}
	
	@Test
	public void GetItem() {
		ArtistaEntity artistaResponse = artistaServiceImpl.getArtista(Long.parseLong("1"));
		assertEquals(artistaResponse.getNome(), artista.getNome());
	}
	
	@Test
	public void Delete() {
		doNothing().when(artistaRepository).delete(any(ArtistaEntity.class));
		artistaServiceImpl.deleteArtista(Long.parseLong("1"));
	}
	
	@Test
	public void Valida() {
		when(artistaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		Exception exception = null;
		
		try {
			artistaServiceImpl.validaArtista(Long.parseLong("1"));
		} catch (Exception ex) {
			exception = ex;
		}
		
		assertEquals("Artista não encontrado. Id: 1", exception.getMessage());
	}
	
}
