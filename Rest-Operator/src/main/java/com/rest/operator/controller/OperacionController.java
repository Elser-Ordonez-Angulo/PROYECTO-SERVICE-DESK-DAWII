package com.rest.operator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.operator.dto.OperacionDTO;
import com.rest.operator.service.OperacionService;

import java.util.List;

@RestController
@RequestMapping("/api/operaciones")
public class OperacionController {

    @Autowired
    private OperacionService operacionService;

    @GetMapping
    public ResponseEntity<List<OperacionDTO>> obtenerOperacionesConDetalles() {
        List<OperacionDTO> operaciones = operacionService.obtenerOperacionesConDetalles();
        return ResponseEntity.ok(operaciones);
    }
}