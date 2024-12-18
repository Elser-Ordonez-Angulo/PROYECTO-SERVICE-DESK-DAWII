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
     * Endpoint para crear una nueva operación asociada a un reclamo
     * @param idReclamo El ID del reclamo asociado a la operación
     * @param descripcion Descripción de la operación
     * @param idOperador El ID del operador asociado
     * @param idEspecialidad El ID de la especialidad asociada
     * @return La operación creada
     */
    @PostMapping("/crear")
    public ResponseEntity<OperacionDTO> crearOperacion(
            @RequestParam("idReclamo") int idReclamo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("idOperador") int idOperador,
            @RequestParam("idEspecialidad") int idEspecialidad) {
        
        try {
            OperacionDTO operacionDTO = operacionService.crearOperacionConReclamo(idReclamo, descripcion, idOperador, idEspecialidad);
            return new ResponseEntity<>(operacionDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint para obtener todas las operaciones con detalles adicionales
     * @return Lista de operaciones con los detalles de los reclamos
     */
    @GetMapping("/todas")
    public ResponseEntity<List<OperacionDTO>> obtenerOperaciones() {
        List<OperacionDTO> operaciones = operacionService.obtenerOperacionesConDetalles();
        return new ResponseEntity<>(operaciones, HttpStatus.OK);
    }

    /**
     * Endpoint para obtener una operación por su ID
     * @param idOperacion El ID de la operación
     * @return La operación solicitada con detalles adicionales
     */
    @GetMapping("/{idOperacion}")
    public ResponseEntity<OperacionDTO> obtenerOperacionPorId(@PathVariable("idOperacion") int idOperacion) {
        OperacionDTO operacionDTO = operacionService.obtenerOperacionPorId(idOperacion);
        if (operacionDTO != null) {
            return new ResponseEntity<>(operacionDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint para actualizar una operación
     * @param idOperacion El ID de la operación a actualizar
     * @param descripcion La nueva descripción de la operación
     * @param idOperador El ID del operador a asociar
     * @param idEspecialidad El ID de la especialidad a asociar
     * @return La operación actualizada
     */
    @PutMapping("/actualizar/{idOperacion}")
    public ResponseEntity<OperacionDTO> actualizarOperacion(
            @PathVariable("idOperacion") int idOperacion,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("idOperador") int idOperador,
            @RequestParam("idEspecialidad") int idEspecialidad) {
        
        try {
            OperacionDTO operacionDTO = operacionService.actualizarOperacion(idOperacion, descripcion, idOperador, idEspecialidad);
            return new ResponseEntity<>(operacionDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint para eliminar una operación
     * @param idOperacion El ID de la operación a eliminar
     * @return Respuesta de éxito o error
     */
    @DeleteMapping("/eliminar/{idOperacion}")
    public ResponseEntity<Void> eliminarOperacion(@PathVariable("idOperacion") int idOperacion) {
        try {
            operacionService.eliminarOperacion(idOperacion);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Código 204 para indicar que la operación fue eliminada
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Código 404 si la operación no existe
        }
    }
}
