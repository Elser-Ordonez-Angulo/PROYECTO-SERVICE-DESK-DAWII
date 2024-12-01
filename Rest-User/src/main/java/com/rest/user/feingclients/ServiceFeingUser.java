package com.rest.user.feingclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rest.user.entity.Reclamo;

@FeignClient(name = "Rest-Service", url = "http://localhost:9003")

public interface ServiceFeingUser {
	
	@PostMapping("/api/reclamos/crearReclamo")
	Reclamo crearReclamo(@RequestBody Reclamo reclamo);

}
