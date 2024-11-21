package com.rest.service.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.service.dto.Dto;
import com.rest.service.model.Reclamo;
import com.rest.service.service.ReclamoService;

@RestController
@RequestMapping("api/reclamos")
public class ReclamoController {

    private final ReclamoService service;

    public ReclamoController(ReclamoService service) {
        this.service = service;
    }

    @PostMapping("crearReclamo")
    public ResponseEntity<Reclamo> registrarReclamo(@RequestBody Dto dto) {
        Reclamo reclamo = service.registrarReclamo(dto);
        return new ResponseEntity<>(reclamo, HttpStatus.CREATED);
    }
}
