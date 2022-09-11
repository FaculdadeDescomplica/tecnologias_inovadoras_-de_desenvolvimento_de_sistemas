package com.evertonogura.springbootapi.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evertonogura.springbootapi.entity.AlbumEntity;
import com.evertonogura.springbootapi.entity.MusicaEntity;
import com.evertonogura.springbootapi.exception.MusicaAlreadyExistsException;
import com.evertonogura.springbootapi.exception.MusicaNotFoundException;
import com.evertonogura.springbootapi.model.MusicaModel;
import com.evertonogura.springbootapi.repository.MusicaRepository;
import com.evertonogura.springbootapi.service.AlbumService;
import com.evertonogura.springbootapi.service.MusicaService;

@Service
public class MusicaServiceImpl implements MusicaService {
	@Autowired
	private MusicaRepository musicaRepository;
	
	@Autowired
	private AlbumService albumService;
	
	@Override
	public MusicaEntity newMusica(MusicaModel newMusica) {
		validaAlbum(newMusica.getIdAlbum());
		validaMusica(newMusica.getIdAlbum(), newMusica.getNome());
		AlbumEntity album = albumService.getAlbum(newMusica.getIdAlbum());
		MusicaEntity musica = new MusicaEntity(newMusica.getNome(), newMusica.getDuracao(), album);
		return musicaRepository.save(musica);
	}

	@Override
	public MusicaEntity updateMusica(Long id, MusicaModel newMusica) {
		validaAlbum(newMusica.getIdAlbum());
		MusicaEntity musica = validaMusica(id);
		musica.setNome(newMusica.getNome());
		musica.setDuracao(newMusica.getDuracao());
		return musicaRepository.save(musica);
	}

	@Override
	public List<MusicaEntity> listMusica(Optional<String> idAlbum) {
		if (idAlbum.isEmpty())
			return musicaRepository.findAll();
		else
			return musicaRepository.findByIdAlbum(Long.parseLong(idAlbum.get()));
	}

	@Override
	public MusicaEntity getMusica(Long id) {
		return validaMusica(id);
	}

	@Override
	public void deleteMusica(Long id) {
		musicaRepository.delete(validaMusica(id));
	}

	@Override
	public MusicaEntity validaMusica(Long id) {
		return musicaRepository.findById(id).orElseThrow(() -> new MusicaNotFoundException(id));
	}
	
	private void validaMusica(Long idAlbum, String nome) {
		if (musicaRepository.findByIdAlbumAndNome(idAlbum, nome).isPresent())
			throw new MusicaAlreadyExistsException(nome);
	}
	
	private void validaAlbum(Long idAlbum) {
		albumService.validaAlbum(idAlbum);
	}
}
