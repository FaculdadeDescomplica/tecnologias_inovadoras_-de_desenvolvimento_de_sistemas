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
import java.util.Optional;

import com.evertonogura.springbootapi.entity.MusicaEntity;
import com.evertonogura.springbootapi.exception.MusicaAlreadyExistsException;
import com.evertonogura.springbootapi.exception.MusicaNotFoundException;
import com.evertonogura.springbootapi.model.MusicaModel;
import com.evertonogura.springbootapi.service.MusicaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:applicationTest.properties")
public class MusicaControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private MusicaService musicaService;
	
	private MusicaEntity musica;
	private List<MusicaEntity> musicas;
	
	@BeforeEach
	public void SetUp() {
		musica = new MusicaEntity(Long.parseLong("1"), "Teste", 3.59);
		musicas = new ArrayList<MusicaEntity>();
		musicas.add(musica);
		when(musicaService.newMusica(any(MusicaModel.class))).thenReturn(musica);
		when(musicaService.updateMusica(anyLong(), any(MusicaModel.class))).thenReturn(musica);
		when(musicaService.listMusica(Optional.ofNullable(null))).thenReturn(musicas);
		when(musicaService.getMusica(anyLong())).thenReturn(musica);
		doNothing().when(musicaService).deleteMusica(anyLong());
	}
	
	@Test
	public void Post() throws NumberFormatException, Exception {
		mvc.perform(post("/api/musicas")
				.content(asJsonString(musica))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isCreated())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.nome", is("Teste")))
			    .andExpect(jsonPath("$.duracao", is(3.59)));
	}

	@Test
	public void PostAlreadyExists() throws NumberFormatException, Exception {
		doThrow(new MusicaAlreadyExistsException("Teste")).when(musicaService).newMusica(any(MusicaModel.class));
		mvc.perform(post("/api/musicas")
				.content(asJsonString(musica))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isBadRequest())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(result -> assertTrue(result.getResolvedException() instanceof MusicaAlreadyExistsException))
			    .andExpect(jsonPath("$.detail", is("Música já existe. Nome: Teste")));
	}
	
	@Test
	public void Put() throws Exception {
		mvc.perform(put("/api/musicas/1")
				.content(asJsonString(musica))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.nome", is("Teste")))
			    .andExpect(jsonPath("$.duracao", is(3.59)));
	}

	@Test
	public void PutNotFound() throws Exception {
		doThrow(new MusicaNotFoundException(Long.parseLong("1"))).when(musicaService).updateMusica(anyLong(), any(MusicaModel.class));
		mvc.perform(put("/api/musicas/1")
				.content(asJsonString(musica))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isNotFound())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(result -> assertTrue(result.getResolvedException() instanceof MusicaNotFoundException))
			    .andExpect(jsonPath("$.detail", is("Música não encontrada. Id: 1")));
	}
	
	@Test
	public void GetList() throws Exception {
		mvc.perform(get("/api/musicas")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$[0].nome", is("Teste")))
			    .andExpect(jsonPath("$[0].duracao", is(3.59)));
	}
	
	@Test
	public void GetItem() throws Exception {
		mvc.perform(get("/api/musicas/1")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.nome", is("Teste")))
			    .andExpect(jsonPath("$.duracao", is(3.59)));
	}
	
	@Test
	public void Delete() throws Exception {
		mvc.perform(delete("/api/musicas/1")
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
