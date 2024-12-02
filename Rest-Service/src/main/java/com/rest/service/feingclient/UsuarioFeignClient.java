package com.rest.service.feingclient;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rest.service.dto.UsuarioDto;

// Define el microservicio que consumirás
@FeignClient(name = "rest-user", url = "http://localhost:9004/api/usuario")
public interface UsuarioFeignClient {
    
    // Método para obtener usuario por DNI
    @GetMapping("/buscar/dni/{dniUsuario}")
    UsuarioDto obtenerUsuarioPorDni(@PathVariable("dniUsuario") int dniUsuario);
}
