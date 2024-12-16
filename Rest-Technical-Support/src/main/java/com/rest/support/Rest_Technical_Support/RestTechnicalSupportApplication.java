package com.rest.support.Rest_Technical_Support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient


public class RestTechnicalSupportApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTechnicalSupportApplication.class, args);
	}

}
