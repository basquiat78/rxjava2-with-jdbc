package com.basquiat.config;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.basquiat.service.jazz.handler.JazzAlbumHandler;

/**
 * RxJava WebFluxRouter Configuration
 * created By basquiat
 *
 */
@EnableWebFlux
@Configuration
public class WebFluxRouter extends DelegatingWebFluxConfiguration {

	@Autowired
	private JazzAlbumHandler jazzAlbumHandler;
	
	@Bean
    public RouterFunction<ServerResponse> jazzAlbumRouter() {
        return route(GET("/albums").and(accept(APPLICATION_JSON)), jazzAlbumHandler::findAll)
        	   .andRoute(GET("/albums/{albumId}").and(accept(APPLICATION_JSON)), jazzAlbumHandler::findByAlbumId)
        	   .andRoute(GET("/albums/musician/{musician}").and(accept(APPLICATION_JSON)), jazzAlbumHandler::findByMusician)
        	   .andRoute(GET("/albums/title/{albumTitle}").and(accept(APPLICATION_JSON)), jazzAlbumHandler::findByAlbumTitle)
        	   .andRoute(GET("/albums/label/{label}").and(accept(APPLICATION_JSON)), jazzAlbumHandler::findByLabel)
        	   .andRoute(POST("/albums").and(accept(APPLICATION_JSON)), jazzAlbumHandler::save)
        	   .andRoute(PUT("/albums").and(accept(APPLICATION_JSON)), jazzAlbumHandler::update)
        	   .andRoute(DELETE("/albums/{albumId}").and(accept(APPLICATION_JSON)), jazzAlbumHandler::deleteByAlbumId);
    }

}
