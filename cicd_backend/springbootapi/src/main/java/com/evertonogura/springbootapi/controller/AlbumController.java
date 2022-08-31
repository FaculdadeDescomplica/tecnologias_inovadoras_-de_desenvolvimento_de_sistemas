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

import com.evertonogura.springbootapi.entity.AlbumEntity;
import com.evertonogura.springbootapi.model.AlbumModel;
import com.evertonogura.springbootapi.service.AlbumService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class AlbumController {
	@Autowired
	private AlbumService albumService;
	
	@PostMapping("/albuns")
	public ResponseEntity<AlbumEntity> newAlbum(@RequestBody AlbumModel newAlbum) {
		return ResponseEntity.status(HttpStatus.CREATED).body(albumService.newAlbum(newAlbum));
	}
	
	@PutMapping("/albuns/{id}")
	public ResponseEntity<AlbumEntity> replaceAlbum(@PathVariable Long id, @RequestBody AlbumModel newAlbum) {
		return ResponseEntity.status(HttpStatus.OK).body(albumService.updateAlbum(id, newAlbum));
	}
	
	@GetMapping("/albuns")
	public ResponseEntity<List<AlbumEntity>> all(@RequestParam(name = "idArtista") Optional<String> idArtista) {
		return ResponseEntity.status(HttpStatus.OK).body(albumService.listAlbum(idArtista));
	}
	
	@GetMapping("/albuns/{id}")
	public ResponseEntity<AlbumEntity> one(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(albumService.getAlbum(id));
	}
	
	@DeleteMapping("/albuns/{id}")
	public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
		albumService.deleteAlbum(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
