package com.cognizant.rules;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
public class RulesMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RulesMsApplication.class, args);
	}

	// Bean configuration for swagger
	@Bean
	public Docket productApi() {
		Set<String> responseProduceType = new HashSet<String>();
		responseProduceType.add("application/json");
		responseProduceType.add("application/xml");
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().useDefaultResponseMessages(false).genericModelSubstitutes(ResponseEntity.class)
				.produces(responseProduceType).consumes(responseProduceType).apiInfo(apiInfo());

	}

	// Information of the API service
	private ApiInfo apiInfo() {

		@SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo("Rules REST Service", "The Rules microservice will be responsible for interacting with a rules engine to evaluate certain rules that is applicable prior to performing transactions ", "API", "Terms of services",
				"", "License of API", "API License URL");
		return apiInfo;

	}

}
