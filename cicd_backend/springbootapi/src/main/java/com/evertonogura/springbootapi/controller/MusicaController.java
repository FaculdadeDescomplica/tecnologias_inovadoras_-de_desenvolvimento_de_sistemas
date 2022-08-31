package com.evertonogura.springbootapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evertonogura.springbootapi.entity.MusicaEntity;
import com.evertonogura.springbootapi.model.MusicaModel;
import com.evertonogura.springbootapi.service.MusicaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class MusicaController {
	@Autowired
	private MusicaService musicaService;
	
	@PostMapping("/musicas")
	public ResponseEntity<MusicaEntity> newMusica(@RequestBody MusicaModel newMusica) {
		return ResponseEntity.status(HttpStatus.CREATED).body(musicaService.newMusica(newMusica));
	}
	
	@PutMapping("/musicas/{id}")
	public ResponseEntity<MusicaEntity> replaceAlbum(@PathVariable Long id, @RequestBody MusicaModel newMusica) {
		return ResponseEntity.status(HttpStatus.OK).body(musicaService.updateMusica(id, newMusica));
	}
	
	@GetMapping("/musicas")
	public ResponseEntity<List<MusicaEntity>> all(@RequestParam(name = "idAlbum") Optional<String> idAlbum) {
		return ResponseEntity.status(HttpStatus.OK).body(musicaService.listMusica(idAlbum));
	}
	
	@GetMapping("/musicas/{id}")
	public ResponseEntity<MusicaEntity> one(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(musicaService.getMusica(id));
	}
	
	@DeleteMapping("/musicas/{id}")
	public ResponseEntity<Void> deleteMusica(@PathVariable Long id) {
		musicaService.deleteMusica(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
