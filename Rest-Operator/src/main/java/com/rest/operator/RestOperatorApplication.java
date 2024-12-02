package com.rest.operator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestOperatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestOperatorApplication.class, args);
	}

}
