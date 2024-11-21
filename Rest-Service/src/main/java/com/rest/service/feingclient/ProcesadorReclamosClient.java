package com.rest.service.feingclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rest.service.model.Reclamo;

@FeignClient(name = "procesadorReclamos", url = "http://localhost:9004") // Cambia la URL según corresponda
public interface ProcesadorReclamosClient {

    @PostMapping("/procesarReclamo")
    void enviarReclamo(@RequestBody Reclamo reclamo);
}
