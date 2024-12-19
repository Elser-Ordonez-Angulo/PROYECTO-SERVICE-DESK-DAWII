package com.restsuport.feingClient;

import com.restsuport.dto.OperacionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "rest-operator", url = "http://localhost:9006")
public interface OperacionClient {

    // Endpoint corregido para obtener todas las operaciones
    @GetMapping("/api/operaciones/todas")
    List<OperacionDTO> getAllOperaciones();

    // Endpoint para obtener una operación por ID
    @GetMapping("/api/operaciones/{idOperacion}")
    OperacionDTO getOperacionById(@PathVariable("idOperacion") int idOperacion);
}
