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
		Mono<JazzAlbum> result = request.bodyToMono(JazzAlbum.class).flatMap(mapper -> jazzAlbumService.save(mapper));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, JazzAlbum.class);
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
	 * find album by album id
	 * @param request
	 * @return Mono<ServerResponse>
	 */
	public Mono<ServerResponse> findByAlbumId(ServerRequest request) {
		String albumId = request.pathVariable("albumId");
		Mono<JazzAlbum> mono = jazzAlbumService.findByAlbumId(albumId);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(mono, JazzAlbum.class);
	}

	/**
	 * find albums by musician
	 * @param request
	 * @return Mono<ServerResponse>
	 */
	public Mono<ServerResponse> findByMusician(ServerRequest request) {
		String musician = request.pathVariable("musician");
		Flux<JazzAlbum> flux = jazzAlbumService.findByMusician(musician);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(flux, JazzAlbum.class);
	}
	
	/**
	 * find albums by album title
	 * @param request
	 * @return Mono<ServerResponse>
	 */
	public Mono<ServerResponse> findByAlbumTitle(ServerRequest request) {
		String albumTitle = request.pathVariable("albumTitle");
		Flux<JazzAlbum> flux = jazzAlbumService.findByAlbumTitle(albumTitle);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(flux, JazzAlbum.class);
	}
	
	/**
	 * find albums by label
	 * @param request
	 * @return Mono<ServerResponse>
	 */
	public Mono<ServerResponse> findByLabel(ServerRequest request) {
		String label = request.pathVariable("label");
		Flux<JazzAlbum> flux = jazzAlbumService.findByLabel(label);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(flux, JazzAlbum.class);
	}

}
