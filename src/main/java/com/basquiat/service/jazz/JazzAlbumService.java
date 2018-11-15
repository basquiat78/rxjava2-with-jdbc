package com.basquiat.service.jazz;

import java.util.List;
import java.util.UUID;

import org.davidmoten.rx.jdbc.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basquiat.service.jazz.domain.JazzAlbum;

import io.reactivex.Flowable;
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
	private Database database;
	
	/**
	 * insert jazz album
	 * doOnError for rdbms info encapsuled
	 * @param jazzAlbumDTO
	 * @return Mono<Integer>
	 */
	public Mono<Integer> insertJazzAlbum(JazzAlbum jazzAlbum) {
		Flowable<Integer> flowable = database.update("INSERT INTO JAZZ_ALBUM(album_id, musician, album_title, label, release_year) values(?, ?, ?, ?, ?)")
				  						  	 .parameters(UUID.randomUUID().toString(), 
					  						  			 jazzAlbum.musician(), 
					  						  			 jazzAlbum.albumTitle(), 
					  						  			 jazzAlbum.label(), 
					  						  			 jazzAlbum.releaseYear())
				  						  	 .counts()
				  						  	 .doOnError(throwable -> {
				  						          throw new RuntimeException("unexcepted");
				  						      });
		return Mono.from(flowable);
	}
	
	
	/**
	 * get jazz album List
	 * @return Mono<List<JazzAlbum>>
	 */
	public Mono<List<JazzAlbum>> getJazzAlbumList() {
		Flowable<JazzAlbum> flowable = database.select("SELECT album_id, musician, album_title, label, release_year FROM JAZZ_ALBUM")
												  .autoMap(JazzAlbum.class);
		
		return Flux.from(flowable).collectList();
	}

	/**
	 * get Transfer History By txId
	 * @param albumId
	 * @return Mono<JazzAlbum>
	 */
	public Mono<JazzAlbum> getJazzAlbumById(String albumId) {
		Flowable<JazzAlbum> flowable = database.select("SELECT album_id, musician, album_title, label, release_year FROM JAZZ_ALBUM WHERE album_id=?")
												  .parameter(albumId)
												  .autoMap(JazzAlbum.class);
		return Mono.from(flowable);
	}

}
