package com.basquiat.config;

import java.util.concurrent.TimeUnit;

import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.pool.DatabaseType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * RxJava Database Configuration
 * created By basquiat
 *
 */
@Component
@Configuration
public class DatabaseConfig {

	private Database database;
	
	/**
	 * Constructor
	 * nonBlocking config for rxjava2
	 * @param url
	 * @param user
	 * @param password
	 * @param maxPoolSize
	 * @throws SQLException 
	 */
	public DatabaseConfig(@Value("${datasource.url}") final String url,
						  @Value("${datasource.username}") final String user,
						  @Value("${datasource.password}") final String password,
						  @Value("${datasource.hikari.maximumPoolSize}") final Integer maxPoolSize) {
		
		/*
		 * non-blocking setup 
		 */
		database = Database.nonBlocking()
			    		   .url(url)
			    		   .user(user)
			    		   .password(password)
			    		   .maxIdleTime(30, TimeUnit.MINUTES)
						   .healthCheck(DatabaseType.MYSQL)
						   .idleTimeBeforeHealthCheck(5, TimeUnit.SECONDS)
						   .connectionRetryInterval(30, TimeUnit.SECONDS)
						   .maxPoolSize(maxPoolSize)
						   .build();
		
	}
	
	/**
	 * create bean database
	 * @return Database
	 */
	public @Bean Database database() {
		return this.database;
	}

}
