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
		jazzAlbum.setReleaseYear("20182");

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
	
	//@Test
	public void updateTest() {
		JazzAlbum jazzAlbum = new JazzAlbum();
		jazzAlbum.setAlbumId("JAZZ-5e3749c6-2804-4e06-89f1-081f582bab1f");
		jazzAlbum.setMusician("Pablo Held");
		jazzAlbum.setAlbumTitle("Investigations");
		jazzAlbum.setLabel("Edition Records");
		jazzAlbum.setReleaseYear("2018");
		ResponseSpec responseSpec = webTestClient.put()
												 .uri("/albums")
												 .body(BodyInserters.fromObject(jazzAlbum))
												 .exchange()
												 .expectStatus().isOk();
		System.out.println(responseSpec.returnResult(JazzAlbum.class));
	}
	
	//@Test
	public void deleteByAlbumIdTest() {
		ResponseSpec responseSpec = webTestClient.delete()
												 .uri("/albums/JAZZ-9d51af42-81c9-4c0b-bf67-110950c03090")
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
