package com.basquiat.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.reactive.function.BodyInserters;

import com.basquiat.service.jazz.domain.JazzAlbum;
import com.basquiat.service.jazz.domain.JazzAlbumDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTest {

	@Autowired
    private WebTestClient webTestClient;
	
	//@Test
	public void saveTest() throws JsonProcessingException {
	
		JazzAlbumDTO jazzAlbumDTO = new JazzAlbumDTO();
		jazzAlbumDTO.setMusician("André Previn");
		jazzAlbumDTO.setAlbumTitle("Pal Joey");
		jazzAlbumDTO.setLabel("Contemporary Records");
		jazzAlbumDTO.setReleaseYear("1957");

		webTestClient.post()
					 .uri("/albums")
					 .body(BodyInserters.fromObject(jazzAlbumDTO))
					 .exchange()
					 .expectStatus().isOk();
	}
	
	@Test
	public void getJazzAlbumTest() throws JsonProcessingException {
	
		ResponseSpec responseSpec = webTestClient.get()
												 .uri("/albums/ea9c96e7-dfe4-449b-8ca5-a560c4486a3f")
												 .exchange()
												 .expectStatus().isOk();
		System.out.println(responseSpec.returnResult(JazzAlbum.class));
	}
	
	//@Test
	public void  getJazzAlbumListTest() throws JsonProcessingException {
	
		ResponseSpec responseSpec = webTestClient.get()
												 .uri("/albums")
												 .exchange()
												 .expectStatus().isOk();
		ParameterizedTypeReference<List<JazzAlbum>> typeReference = new ParameterizedTypeReference<List<JazzAlbum>>() {};
		System.out.println(responseSpec.returnResult(typeReference));
	}
	
	//@Test
	public void httpMethodErrorTest() throws JsonProcessingException {
		ResponseSpec responseSpec = webTestClient.post()
												 .uri("/albums")
												 .exchange()
												 .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
		ParameterizedTypeReference<List<JazzAlbum>> typeReference = new ParameterizedTypeReference<List<JazzAlbum>>() {};
		System.out.println(responseSpec.returnResult(typeReference));
	}
	
}
