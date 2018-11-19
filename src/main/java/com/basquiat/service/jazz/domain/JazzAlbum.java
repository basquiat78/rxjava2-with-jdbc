package com.basquiat.service.jazz.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * 
 * Jazz Albums Domain
 * 
 * created by basquiat
 *
 */
@Data
@Document
public class JazzAlbum {

	/**
	 * album id
	 */
	@Id
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
