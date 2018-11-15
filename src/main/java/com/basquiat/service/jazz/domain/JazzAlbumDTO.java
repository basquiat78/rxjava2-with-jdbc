package com.basquiat.service.jazz.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Jazz Albums Domain
 * 
 * created by basquiat
 *
 */
@Setter
@Getter
public class JazzAlbumDTO implements JazzAlbum {

	/**
	 * album id
	 */
	private String albumId;

	/**
	 * musician
	 */
	private String musician;
	
	/**
	 * album title
	 */
	private String albumTitle;
	
	/**
	 * album label
	 */
	private String label;
	
	/**
	 * album release year
	 */
	private String releaseYear;

	@Override
	public String albumId() {
		return this.albumId;
	}

	@Override
	public String musician() {
		return this.musician;
	}

	@Override
	public String albumTitle() {
		return this.albumTitle;
	}

	@Override
	public String label() {
		return this.label;
	}

	@Override
	public String releaseYear() {
		return this.releaseYear;
	}
	
}
