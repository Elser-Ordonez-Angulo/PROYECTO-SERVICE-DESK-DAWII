package com.rest.service.service;
import org.springframework.stereotype.Service;

import com.rest.service.dto.Dto;
import com.rest.service.feingclient.ProcesadorReclamosClient;
import com.rest.service.model.Reclamo;
import com.rest.service.repository.ReclamoRepository;

import java.time.LocalDateTime;

@Service
public class ReclamoService {

    private final ReclamoRepository repository;
    private final ProcesadorReclamosClient feignClient;

    public ReclamoService(ReclamoRepository repository, ProcesadorReclamosClient feignClient) {
        this.repository = repository;
        this.feignClient = feignClient;
    }

    public Reclamo registrarReclamo(Dto dto) {
        // Crear el objeto Reclamo
        Reclamo reclamo = new Reclamo();
        reclamo.setUsuarioId(dto.getUsuarioId());
        reclamo.setMensaje(dto.getMensaje());
        reclamo.setCategoria(dto.getCategoria());
        reclamo.setEstado("pendiente");
        reclamo.setFechaHora(LocalDateTime.now());

        // Guardar en la base de datos
        Reclamo reclamoGuardado = repository.save(reclamo);

        // Enviar al siguiente microservicio
        feignClient.enviarReclamo(reclamoGuardado);

        return reclamoGuardado;
    }
}
