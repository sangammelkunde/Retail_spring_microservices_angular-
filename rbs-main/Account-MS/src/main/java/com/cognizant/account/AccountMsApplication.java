package com.cognizant.account;

import java.util.Collections;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cognizant.account.model.Account;
import com.cognizant.account.repository.AccountRepository;
import com.sun.el.stream.Stream;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * The main class of the Account-MS which loads all the components and starts the account microservice.
 */

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class AccountMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountMsApplication.class, args);
	}
	
	
//	@Bean
//    ApplicationRunner init(AccountRepository repository) {
//        // Save our starter set of books
//        return args -> {
//            Stream.of(new Account(79899900,"123456789",10500, "Savings", "Nikhil"), new Account(69899900,"123456799",10500, "Savings", "Kumar"),
//            		new Account(59899900,"123456889",10500, "Current", "Thakur")).forEach(acc -> {
//                repository.save(acc);
//            });
//            //retrieve them all, and print so that we see everything is wired up correctly
//            repository.findAll().forEach(System.out::println);
//        };
//    }

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cognizant.account.controller")).build()
				.apiInfo(apiInfo());

	}  

	/* API Info for Swagger Dashboard. */
	private ApiInfo apiInfo() {
		return new ApiInfo("Account Micro Service",
				"The Account Microservice will perform the following tasks: \n"
				+ "Creating an Account for a Customer \n"
				+ "Fetching Accounts related to a Customer as a summary information \n"
				+ "Fetching account statement for a particular customer based on a date range \n"
				+ "Depositing to a customer’s account \n"
				+ "Withdrawing from a customer’s account Retail Banking System.", "API", "Terms of service",
				new Contact("Priyanka", "", ""), "License of API", "",
				Collections.emptyList());
	}

}
