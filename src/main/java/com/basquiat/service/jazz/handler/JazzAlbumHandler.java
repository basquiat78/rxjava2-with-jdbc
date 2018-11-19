package com.basquiat.service.jazz.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.basquiat.service.jazz.JazzAlbumService;
import com.basquiat.service.jazz.domain.JazzAlbum;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * webflux use handler instead controller
 * 
 * created by basquiat
 *
 */
@Component
public class JazzAlbumHandler {

	@Autowired
	private JazzAlbumService jazzAlbumService;
	
	/**
	 * save jazz album
	 * @param request
	 * @return Mono<ServerResponse>
	 */
	public Mono<ServerResponse> save(ServerRequest request) {
		Mono<Integer> result = request.bodyToMono(JazzAlbum.class).flatMap(mapper -> jazzAlbumService.save(mapper));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, Integer.class);
	}
	
	/**
	 * find all jazz album list
	 * @param request
	 * @return Mono<ServerResponse>
	 */
	public Mono<ServerResponse> findAll(ServerRequest request) {
		Flux<JazzAlbum> flux = jazzAlbumService.findAll();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(flux, JazzAlbum.class);
	}
	
	/**
	 * find jazz album by id
	 * @param request
	 * @return Mono<ServerResponse>
	 */
	public Mono<ServerResponse> findById(ServerRequest request) {
		String albumId = request.pathVariable("albumId");
		Mono<JazzAlbum> result = jazzAlbumService.findByAlbumId(albumId);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, JazzAlbum.class);
	}

}
