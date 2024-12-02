package com.rest.operator.controller;

import com.rest.operator.dto.OperacionDTO;
import com.rest.operator.service.OperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operaciones")
public class OperacionController {

    @Autowired
    private OperacionService operacionService;

    /**
     * Crear una nueva operación asociando los datos del reclamo
     */
    @PostMapping("/crear")
    public ResponseEntity<OperacionDTO> crearOperacionConReclamo(
            @RequestParam int idReclamo,
            @RequestParam String descripcion,
            @RequestParam int idOperador,
            @RequestParam int idEspecialidad) {
        try {
            OperacionDTO operacionDTO = operacionService.crearOperacionConReclamo(idReclamo, descripcion, idOperador, idEspecialidad);
            return new ResponseEntity<>(operacionDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo de excepciones para un error en la creación
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtener todas las operaciones con detalles adicionales
     */
    @GetMapping("/todas")
    public ResponseEntity<List<OperacionDTO>> obtenerOperacionesConDetalles() {
        List<OperacionDTO> operaciones = operacionService.obtenerOperacionesConDetalles();
        if (operaciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(operaciones, HttpStatus.OK);
    }

    /**
     * Obtener una operación por su ID con detalles del reclamo
     */
    @GetMapping("/{idOperacion}")
    public ResponseEntity<OperacionDTO> obtenerOperacionPorId(@PathVariable int idOperacion) {
        OperacionDTO operacionDTO = operacionService.obtenerOperacionPorId(idOperacion);
        if (operacionDTO != null) {
            return new ResponseEntity<>(operacionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
