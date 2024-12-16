<<<<<<<< HEAD:Rest-Support/src/main/java/com/support/RestSupportApplication.java
package com.support;
========
package cibertec.edu.pe;
>>>>>>>> 5687db272e58cded0abf841ddef1f3c85812fef9:Identity-Service/Identity-Service/src/main/java/cibertec/edu/pe/IdentityServiceApplication.java

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
<<<<<<<< HEAD:Rest-Support/src/main/java/com/support/RestSupportApplication.java
public class RestSupportApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSupportApplication.class, args);
========
public class IdentityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityServiceApplication.class, args);
>>>>>>>> 5687db272e58cded0abf841ddef1f3c85812fef9:Identity-Service/Identity-Service/src/main/java/cibertec/edu/pe/IdentityServiceApplication.java
	}

}
