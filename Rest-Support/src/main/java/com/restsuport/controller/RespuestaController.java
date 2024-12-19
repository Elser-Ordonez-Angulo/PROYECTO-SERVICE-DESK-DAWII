package com.restsuport.controller;

import com.restsuport.dto.RespuestaRequest;
import com.restsuport.model.Respuesta;
import com.restsuport.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    /**
     * Endpoint para crear una nueva respuesta asociada a una operación.
     * @param descripcionRespuesta La descripción de la respuesta.
     * @param idOperacion El ID de la operación asociada.
     * @return La respuesta creada o un error si la operación no existe.
     */
    @PostMapping("/crear")
    public ResponseEntity<Respuesta> crearRespuesta(@RequestBody RespuestaRequest respuestaRequest) {
        try {
            // Crear la respuesta utilizando el servicio
            Respuesta respuesta = respuestaService.crearRespuesta(
                    respuestaRequest.getDescripcionRespuesta(),
                    respuestaRequest.getIdOperacion()
            );
            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Si la operación no existe, devolver un error 404
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // En caso de otros errores
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Endpoint para obtener todas las respuestas.
     * @return Lista de respuestas.
     */
    @GetMapping("/todas")
    public ResponseEntity<Iterable<Respuesta>> obtenerTodasLasRespuestas() {
        Iterable<Respuesta> respuestas = respuestaService.obtenerTodasLasRespuestas();
        return new ResponseEntity<>(respuestas, HttpStatus.OK);
    }

    /**
     * Endpoint para obtener una respuesta por su ID.
     * @param idRespuesta El ID de la respuesta.
     * @return La respuesta solicitada o un error si no se encuentra.
     */
    @GetMapping("/{idRespuesta}")
    public ResponseEntity<Respuesta> obtenerRespuestaPorId(@PathVariable("idRespuesta") int idRespuesta) {
        Respuesta respuesta = respuestaService.obtenerRespuestaPorId(idRespuesta);
        if (respuesta != null) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
