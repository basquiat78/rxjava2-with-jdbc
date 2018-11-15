package com.basquiat.config;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
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
 * RxJava Database Configuration
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
        return route(GET("/albums/{albumId}").and(accept(APPLICATION_JSON)), jazzAlbumHandler::findById)
        	   .andRoute(GET("/albums").and(accept(APPLICATION_JSON)), jazzAlbumHandler::findAll)
        	   .andRoute(POST("/albums").and(accept(APPLICATION_JSON)), jazzAlbumHandler::save);
    }

}
