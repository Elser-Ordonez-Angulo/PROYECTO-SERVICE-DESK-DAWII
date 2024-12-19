package com.restsuport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.restsuport.dto.OperacionDTO;
import com.restsuport.dto.RespuestaRequest;
import com.restsuport.service.RespuestaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearRespuesta(@RequestBody RespuestaRequest respuestaRequest) {
        try {
            String respuesta = respuestaService.crearRespuesta(
                    respuestaRequest.getDescripcionRespuesta(),
                    respuestaRequest.getIdOperacion(),
                    respuestaRequest.getTipoEspecialista(),
                    respuestaRequest.getIdEspecialista()
            );
            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/operaciones")
    public ResponseEntity<List<OperacionDTO>> listarOperaciones() {
        try {
            Iterable<OperacionDTO> operacionesIterable = respuestaService.listarOperaciones();
            
            // Convertir Iterable a List
            List<OperacionDTO> operaciones = new ArrayList<>();
            operacionesIterable.forEach(operaciones::add);
            
            return new ResponseEntity<>(operaciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
