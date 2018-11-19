package com.basquiat.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.reactive.function.BodyInserters;

import com.basquiat.service.jazz.domain.JazzAlbum;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceTest {

	@Autowired
    private WebTestClient webTestClient;
	
	//@Test
	public void saveTest() {
		JazzAlbum jazzAlbum = new JazzAlbum();
		jazzAlbum.setMusician("Pablo Held");
		jazzAlbum.setAlbumTitle("Investigations");
		jazzAlbum.setLabel("Edition Records");
		jazzAlbum.setReleaseYear("2018");

		webTestClient.post()
					 .uri("/albums")
					 .body(BodyInserters.fromObject(jazzAlbum))
					 .exchange()
					 .expectStatus().isOk();
	}
	
	//@Test
	public void findAllTest() {
		ResponseSpec responseSpec = webTestClient.get()
												 .uri("/albums")
												 .exchange()
												 .expectStatus().isOk();
		System.out.println(responseSpec.returnResult(JazzAlbum.class));
	}
	
	//@Test
	public void findByAlbumIdTest() {
		ResponseSpec responseSpec = webTestClient.get()
												 .uri("/albums/JAZZ-452c5efb-6ad0-409d-a71e-47027a429bf9")
												 .exchange()
												 .expectStatus().isOk();
		System.out.println(responseSpec.returnResult(JazzAlbum.class));
	}
	
	//@Test
	public void findByMusicianTest() {
		ResponseSpec responseSpec = webTestClient.get()
												 .uri("/albums/musician/Keith Jarrett")
												 .exchange()
												 .expectStatus().isOk();
		System.out.println(responseSpec.returnResult(JazzAlbum.class));
	}
	
	//@Test
	public void findByAlbumTitleTest() {
		ResponseSpec responseSpec = webTestClient.get()
												 .uri("/albums/title/The Köln Concert")
												 .exchange()
												 .expectStatus().isOk();
		System.out.println(responseSpec.returnResult(JazzAlbum.class));
	}
	
	//@Test
	public void findByLabelTest() {
		ResponseSpec responseSpec = webTestClient.get()
												 .uri("/albums/label/Edition Records")
												 .exchange()
												 .expectStatus().isOk();
		System.out.println(responseSpec.returnResult(JazzAlbum.class));
	}
	
	@Test
	public void httpMethodErrorTest() {
		ResponseSpec responseSpec = webTestClient.post()
												 .uri("/albums/label/Edition Records")
												 .exchange()
												 .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
		System.out.println(responseSpec.returnResult(JazzAlbum.class));
	}
	
}
