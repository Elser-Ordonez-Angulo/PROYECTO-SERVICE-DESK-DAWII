package com.restsuport.controller;

import com.restsuport.dto.OperacionDTO;
import com.restsuport.model.Developer;
import com.restsuport.service.DeveloperService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    /**
     * Endpoint para registrar la respuesta de un developer.
     * Valida que el idOperacion sea válido antes de guardar la respuesta.
     *
     * @param developer Datos del developer, incluyendo el idOperacion
     * @return Developer guardado o error si idOperacion no existe
     */
    @PostMapping("/respuesta")
    public ResponseEntity<?> registrarRespuesta(@RequestBody Developer developer) {
        try {
            Developer savedDeveloper = developerService.crearRespuesta(developer);
            return new ResponseEntity<>(savedDeveloper, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/operaciones")
    public ResponseEntity<List<OperacionDTO>> listarOperaciones() {
        List<OperacionDTO> operaciones = developerService.listarTodasOperaciones();
        return ResponseEntity.ok(operaciones);
    }
}
