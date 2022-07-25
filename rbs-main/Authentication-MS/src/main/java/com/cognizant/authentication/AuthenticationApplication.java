package com.cognizant.authentication;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The AuthenticationApplication class for starting the application
 * 
 */

@SpringBootApplication
@EnableSwagger2
public class AuthenticationApplication {
	/**
	 * The main method for application
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	/**
	 * Docker Product API
	 * 
	 * @return Base package with API Information for Swagger
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cognizant.authentication.controller")).build()
				.apiInfo(apiInfo());

	}

	/**
	 * API Info for Swagger Dashboard
	 * 
	 */
	private ApiInfo apiInfo() {
		return new ApiInfo("Authentication Micro Service.", "Authenticates the users", "API", "Terms of service",
				new Contact("Nada Ahmad", "", ""), "License of API", "",
				Collections.emptyList());
	}
	

}
