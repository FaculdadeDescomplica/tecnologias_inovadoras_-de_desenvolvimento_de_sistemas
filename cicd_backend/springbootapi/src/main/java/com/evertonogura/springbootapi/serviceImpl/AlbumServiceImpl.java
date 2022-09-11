package com.evertonogura.springbootapi.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evertonogura.springbootapi.entity.AlbumEntity;
import com.evertonogura.springbootapi.entity.ArtistaEntity;
import com.evertonogura.springbootapi.exception.AlbumAlreadyExistsException;
import com.evertonogura.springbootapi.exception.AlbumNotFoundException;
import com.evertonogura.springbootapi.model.AlbumModel;
import com.evertonogura.springbootapi.repository.AlbumRepository;
import com.evertonogura.springbootapi.service.AlbumService;
import com.evertonogura.springbootapi.service.ArtistaService;

@Service
public class AlbumServiceImpl implements AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private ArtistaService artistaService;

	@Override
	public AlbumEntity newAlbum(AlbumModel newAlbum) {
		validaArtista(newAlbum.getIdArtista());
		validaAlbum(newAlbum.getIdArtista(), newAlbum.getNome());
		ArtistaEntity artista = artistaService.getArtista(newAlbum.getIdArtista());
		AlbumEntity album = new AlbumEntity(newAlbum.getNome(), newAlbum.getAno(), artista);
		return albumRepository.save(album);
	}

	@Override
	public AlbumEntity updateAlbum(Long id, AlbumModel newAlbum) {
		validaArtista(newAlbum.getIdArtista());
		AlbumEntity album = validaAlbum(id);
		album.setNome(newAlbum.getNome());
		album.setAno(newAlbum.getAno());
		return albumRepository.save(album);
	}

	@Override
	public List<AlbumEntity> listAlbum(Optional<String> idArtista) {
		if (idArtista.isEmpty())
			return albumRepository.findAll();
		else
			return albumRepository.findByArtista(Long.parseLong(idArtista.get()));
	}

	@Override
	public AlbumEntity getAlbum(Long id) {
		return validaAlbum(id);
	}

	@Override
	public void deleteAlbum(Long id) {
		albumRepository.delete(validaAlbum(id));
	}

	@Override
	public AlbumEntity validaAlbum(Long id) {
		return albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException(id));
	}
	
	private void validaArtista(Long idArtista) {
		artistaService.validaArtista(idArtista);
	}
	
	private void validaAlbum(Long idArtista, String nome) {
		if (albumRepository.findByIdArtistaAndNome(idArtista, nome).isPresent())
			throw new AlbumAlreadyExistsException(nome);
	}
}
