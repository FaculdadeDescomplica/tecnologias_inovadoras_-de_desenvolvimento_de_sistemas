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

import com.evertonogura.springbootapi.entity.AlbumEntity;
import com.evertonogura.springbootapi.exception.AlbumAlreadyExistsException;
import com.evertonogura.springbootapi.exception.AlbumNotFoundException;
import com.evertonogura.springbootapi.model.AlbumModel;
import com.evertonogura.springbootapi.service.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:applicationTest.properties")
public class AlbumControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private AlbumService albumService;
	
	private AlbumEntity album;
	private List<AlbumEntity> albuns;
	
	@BeforeEach
	public void SetUp() {
		album = new AlbumEntity(Long.parseLong("1"), "Teste", 1999);
		albuns = new ArrayList<AlbumEntity>();
		albuns.add(album);
		when(albumService.newAlbum(any(AlbumModel.class))).thenReturn(album);
		when(albumService.updateAlbum(anyLong(), any(AlbumModel.class))).thenReturn(album);
		when(albumService.listAlbum(Optional.ofNullable(null))).thenReturn(albuns);
		when(albumService.getAlbum(anyLong())).thenReturn(album);
		doNothing().when(albumService).deleteAlbum(anyLong());
	}
	
	@Test
	public void Post() throws NumberFormatException, Exception {
		mvc.perform(post("/api/albuns")
				.content(asJsonString(album))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isCreated())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.nome", is("Teste")))
			    .andExpect(jsonPath("$.ano", is(1999)));
	}

	@Test
	public void PostAlreadyExists() throws NumberFormatException, Exception {
		doThrow(new AlbumAlreadyExistsException("Teste")).when(albumService).newAlbum(any(AlbumModel.class));
		mvc.perform(post("/api/albuns")
				.content(asJsonString(album))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isBadRequest())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(result -> assertTrue(result.getResolvedException() instanceof AlbumAlreadyExistsException))
			    .andExpect(jsonPath("$.detail", is("Album já existe. Nome: Teste")));
	}
	
	@Test
	public void Put() throws Exception {
		mvc.perform(put("/api/albuns/1")
				.content(asJsonString(album))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.nome", is("Teste")))
			    .andExpect(jsonPath("$.ano", is(1999)));
	}

	@Test
	public void PutNotFound() throws Exception {
		doThrow(new AlbumNotFoundException(Long.parseLong("1"))).when(albumService).updateAlbum(anyLong(), any(AlbumModel.class));
		mvc.perform(put("/api/albuns/1")
				.content(asJsonString(album))
			    .contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isNotFound())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(result -> assertTrue(result.getResolvedException() instanceof AlbumNotFoundException))
			    .andExpect(jsonPath("$.detail", is("Album não encontrado. Id: 1")));
	}
	
	@Test
	public void GetList() throws Exception {
		mvc.perform(get("/api/albuns")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$[0].nome", is("Teste")))
			    .andExpect(jsonPath("$[0].ano", is(1999)));
	}
	
	@Test
	public void GetItem() throws Exception {
		mvc.perform(get("/api/albuns/1")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
			    .andExpect(content()
			    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			    .andExpect(jsonPath("$.nome", is("Teste")))
			    .andExpect(jsonPath("$.ano", is(1999)));
	}
	
	@Test
	public void Delete() throws Exception {
		mvc.perform(delete("/api/albuns/1")
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
