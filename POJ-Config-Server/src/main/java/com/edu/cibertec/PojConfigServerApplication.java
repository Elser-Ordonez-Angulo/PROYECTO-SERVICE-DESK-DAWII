package com.edu.cibertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class PojConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PojConfigServerApplication.class, args);
	}

}
