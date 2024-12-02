package com.rest.operator.feingclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rest.operator.dto.ReclamoResponseDto;

// Define el microservicio que consumirás
@FeignClient(name = "rest-service", url = "http://localhost:9003/api/reclamo")
public interface OperatorFeignService {

    // Método para obtener reclamo por ID (lo adaptamos a tu ruta)
    @GetMapping("/buscar/{idReclamo}")
    ReclamoResponseDto obtenerReclamoPorId(@PathVariable("idReclamo") int idReclamo);
}
