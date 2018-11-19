package com.basquiat.service.jazz.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.basquiat.service.jazz.domain.JazzAlbum;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * Jazz Album Repository
 * 
 * created by basquiat
 *
 */
public interface JazzAlbumRepository extends ReactiveMongoRepository<JazzAlbum, String> {
	
	Flux<JazzAlbum> findAll();
	Flux<JazzAlbum> findByMusician(String musician);
	Flux<JazzAlbum> findByAlbumTitle(String albumTitle);
	Flux<JazzAlbum> findByLabel(String label);
	
	Mono<JazzAlbum> findByAlbumId(String txId);

}
