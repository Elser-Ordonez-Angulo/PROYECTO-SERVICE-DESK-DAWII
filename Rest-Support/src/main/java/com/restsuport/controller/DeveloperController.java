package com.restsuport.controller;

import com.restsuport.dto.OperacionDTO;
import com.restsuport.model.Developer;
import com.restsuport.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            // Si la operación no existe, se maneja el error con un mensaje adecuado
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint para listar todas las operaciones.
     *
     * @return Lista de todas las operaciones disponibles
     */
    @GetMapping("/operaciones")
    public ResponseEntity<List<OperacionDTO>> listarOperaciones() {
        List<OperacionDTO> operaciones = developerService.listarTodasOperaciones();
        return ResponseEntity.ok(operaciones);
    }

    /**
     * Endpoint para eliminar una respuesta de un developer por su ID.
     *
     * @param id ID del Developer a eliminar
     * @return Respuesta con estado OK si se eliminó correctamente o un mensaje de error
     */
    @DeleteMapping("/respuesta/{id}")
    public ResponseEntity<?> eliminarRespuesta(@PathVariable Integer id) {
        try {
            developerService.eliminarRespuesta(id);
            return new ResponseEntity<>("Respuesta eliminada correctamente", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Si el developer no existe, se maneja el error con un mensaje adecuado
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
