package com.support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RestSupportApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSupportApplication.class, args);
	}

}
