package com.rest.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@SpringBootApplication

@EnableDiscoveryClient
@EnableFeignClients
public class RestUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestUserApplication.class, args);
	}

}
