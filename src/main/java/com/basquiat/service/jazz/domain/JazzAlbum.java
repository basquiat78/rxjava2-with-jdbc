package com.basquiat.service.jazz.domain;

import org.davidmoten.rx.jdbc.annotations.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Jazz Albums Domain
 * 
 * created by basquiat
 *
 */
public interface JazzAlbum {

	/**
	 * album id
	 */
	@JsonProperty("albumId")
	@Column("album_id")
	String albumId();

	/**
	 * musician
	 */
	@JsonProperty("musician")
	@Column("musician")
	String musician();
	
	/**
	 * album title
	 */
	@JsonProperty("albumTitle")
	@Column("album_title")
	String albumTitle();
	
	/**
	 * album label
	 */
	@JsonProperty("label")
	@Column("label")
	String label();
	
	/**
	 * album release year
	 */
	@JsonProperty("releaseYear")
	@Column("release_year")
	String releaseYear();
	
}
