package com.evertonogura.springbootapi.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.evertonogura.springbootapi.entity.ArtistaEntity;
import com.evertonogura.springbootapi.model.ArtistaModel;
import com.evertonogura.springbootapi.service.ArtistaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class ArtistaController {
	@Autowired
	private ArtistaService artistaService;
	
	@PostMapping("/artistas")
	public ResponseEntity<ArtistaEntity> newArtista(@RequestBody ArtistaModel newArtista) {
		return ResponseEntity.status(HttpStatus.CREATED).body(artistaService.newArtista(newArtista));
	}
	
	@PutMapping("/artistas/{id}")
	public ResponseEntity<ArtistaEntity> replaceArtista(@PathVariable Long id, @RequestBody ArtistaModel newArtista) {
		return ResponseEntity.status(HttpStatus.OK).body(artistaService.updateArtista(id, newArtista));
	}
	
	@GetMapping("/artistas")
	public ResponseEntity<List<ArtistaEntity>> all() {
		return ResponseEntity.status(HttpStatus.OK).body(artistaService.listArtista());
	}
	
	@GetMapping("/artistas/{id}")
	public ResponseEntity<ArtistaEntity> one(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(artistaService.getArtista(id));
	}
	
	@DeleteMapping("/artistas/{id}")
	public ResponseEntity<Void> deleteArtista(@PathVariable Long id) {
		artistaService.deleteArtista(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
