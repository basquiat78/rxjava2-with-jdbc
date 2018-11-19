package com.basquiat.service.jazz;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basquiat.service.jazz.domain.JazzAlbum;
import com.basquiat.service.jazz.repo.JazzAlbumRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * jazzAlbum Service
 * 
 * created by basquiat
 *
 */
@Service("jazzAlbumService")
public class JazzAlbumService {

	@Autowired
	private JazzAlbumRepository jazzAlbumRepository;
	
	/**
	 * create jazz album
	 * @param jazzAlbum
	 * @return Mono<JazzAlbum>
	 */
	public Mono<JazzAlbum> save(JazzAlbum jazzAlbum) {
		String albumId = "JAZZ-"+UUID.randomUUID().toString();
		jazzAlbum.setAlbumId(albumId);
		return jazzAlbumRepository.save(jazzAlbum);
	}
	
	/**
	 * find all jazz album
	 * @return Flux<JazzAlbum>
	 */
	public Flux<JazzAlbum> findAll() {
		return jazzAlbumRepository.findAll();
	}

	/**
	 * find jazz album by album id
	 * @param albumId
	 * @return Mono<JazzAlbum>
	 */
	public Mono<JazzAlbum> findByAlbumId(String albumId) {
		return jazzAlbumRepository.findByAlbumId(albumId);
	}
	
	/**
	 * find jazz album by musician
	 * @param musician
	 * @return Flux<JazzAlbum>
	 */
	public Flux<JazzAlbum> findByMusician(String musician) {
		return jazzAlbumRepository.findByMusician(musician);
	}
	
	/**
	 * find jazz album by album title
	 * @param albumTitle
	 * @return Flux<JazzAlbum>
	 */
	public Flux<JazzAlbum> findByAlbumTitle(String albumTitle) {
		return jazzAlbumRepository.findByAlbumTitle(albumTitle);
	}

	/**
	 * find jazz album by label
	 * @param label
	 * @return Flux<JazzAlbum>
	 */
	public Flux<JazzAlbum> findByLabel(String label) {
		return jazzAlbumRepository.findByLabel(label);
	}
	
}
