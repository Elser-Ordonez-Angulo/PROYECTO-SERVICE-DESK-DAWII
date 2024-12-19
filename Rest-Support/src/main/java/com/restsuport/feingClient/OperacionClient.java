package com.restsuport.feingClient;

import com.restsuport.dto.OperacionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rest-operator", url = "http://localhost:9006")
public interface OperacionClient {

    /**
     * Obtiene una operación por su ID desde el microservicio Rest-Operator
     * @param idOperacion El ID de la operación
     * @return El objeto OperacionDTO con los detalles de la operación
     */
    @GetMapping("/api/operaciones/{idOperacion}")
    OperacionDTO getOperacionById(@PathVariable("idOperacion") int idOperacion);
}
