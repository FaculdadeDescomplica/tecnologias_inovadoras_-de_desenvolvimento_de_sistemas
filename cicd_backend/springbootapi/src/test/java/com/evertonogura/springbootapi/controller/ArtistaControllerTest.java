package com.evertonogura.springbootapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.evertonogura.springbootapi.entity.ArtistaEntity;
import com.evertonogura.springbootapi.exception.ArtistaAlreadyExistsException;
import com.evertonogura.springbootapi.exception.ArtistaNotFoundException;
import com.evertonogura.springbootapi.model.ArtistaModel;
import com.evertonogura.springbootapi.service.ArtistaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:applicationTest.properties")
public class ArtistaControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private ArtistaService artistaService;
	
	private ArtistaEntity artista;
	private List<ArtistaEntity> artistas;
	
	@BeforeEach
	public void SetUp() {
		artista = new ArtistaEntity(Long.parseLong("1"), "Teste");
		artistas = new ArrayList<ArtistaEntity>();
		artistas.add(artista);
		when(artistaService.newArtista(any(ArtistaModel.class))).thenReturn(artista);
		when(artistaService.updateArtista(anyLong(), any(ArtistaModel.class))).thenReturn(artista);
		when(artistaService.listArtista()).thenReturn(artistas);
		when(artistaService.getArtista(anyLong())).thenReturn(artista);
		doNothing().when(artistaService).deleteArtista(anyLong());
	}
	
	@Test
	public void Post() throws NumberFormatException, Exception {
		mvc.perform(post("/api/artistas")
				.content(asJsonString(artista))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isCreated())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.nome", is("Teste")));
	}
	
	@Test
	public void PostAlreadyExists() throws NumberFormatException, Exception {
		doThrow(new ArtistaAlreadyExistsException("Teste")).when(artistaService).newArtista(any(ArtistaModel.class));
		mvc.perform(post("/api/artistas")
				.content(asJsonString(artista))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isBadRequest())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(result -> assertTrue(result.getResolvedException() instanceof ArtistaAlreadyExistsException))
			    .andExpect(jsonPath("$.detail", is("Artista já existe. Nome: Teste")));
	}
	
	@Test
	public void Put() throws Exception {
		mvc.perform(put("/api/artistas/1")
				.content(asJsonString(artista))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.nome", is("Teste")));
	}

	@Test
	public void PutNotFound() throws Exception {
		doThrow(new ArtistaNotFoundException(Long.parseLong("1"))).when(artistaService).updateArtista(anyLong(), any(ArtistaModel.class));
		mvc.perform(put("/api/artistas/1")
				.content(asJsonString(artista))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isNotFound())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(result -> assertTrue(result.getResolvedException() instanceof ArtistaNotFoundException))
			    .andExpect(jsonPath("$.detail", is("Artista não encontrado. Id: 1")));
	}
	
	@Test
	public void GetList() throws Exception {
		mvc.perform(get("/api/artistas")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$[0].nome", is("Teste")));
	}
	
	@Test
	public void GetItem() throws Exception {
		mvc.perform(get("/api/artistas/1")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.nome", is("Teste")));
	}
	
	@Test
	public void Delete() throws Exception {
		mvc.perform(delete("/api/artistas/1")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isNoContent());
	}
	
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	    	throw new RuntimeException(e);
	    }
	}
	
}
