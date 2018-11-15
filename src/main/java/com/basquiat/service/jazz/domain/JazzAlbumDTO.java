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
public class JazzAlbumDTO {

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
	
}
