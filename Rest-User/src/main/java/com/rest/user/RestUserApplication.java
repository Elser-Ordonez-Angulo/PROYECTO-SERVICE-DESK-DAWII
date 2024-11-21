package com.rest.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@SpringBootApplication
public class RestUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestUserApplication.class, args);
	}

}
