package com.rest.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.service.dtoRequest.ReclamoRequest;
import com.rest.service.model.Reclamo;
import com.rest.service.service.ReclamoService;

@RestController
@RequestMapping("/api/reclamo")
public class ReclamoController {

    @Autowired
    private ReclamoService reclamoService;

    /**
     * Crear un reclamo
     * @param dniUsuario DNI del usuario
     * @param descripcion Descripción del reclamo
     * @return Reclamo creado
     */
    @PostMapping("/crear")
    public ResponseEntity<Reclamo> crearReclamo(@RequestBody ReclamoRequest reclamoRequest) {
        try {
            Reclamo reclamo = reclamoService.crearReclamo(reclamoRequest);
            return ResponseEntity.ok(reclamo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    /**
     * Obtener todos los reclamos
     * @return Lista de reclamos
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Reclamo>> listarReclamos() {
        List<Reclamo> reclamos = reclamoService.listarReclamos();
        return ResponseEntity.ok(reclamos);
    }
    //listar por dni
    @GetMapping("/listarPorDni/{dniUsuario}")
    public ResponseEntity<List<Reclamo>> listarReclamosPorDni(@PathVariable int dniUsuario) {
        try {
            // Llamamos al servicio para listar los reclamos con el dniUsuario
            List<Reclamo> reclamos = reclamoService.listarReclamosPorDni(dniUsuario);

            if (reclamos.isEmpty()) {
                return ResponseEntity.noContent().build(); // Si no hay reclamos, respondemos con código 204
            }
            return ResponseEntity.ok(reclamos); // Si hay reclamos, respondemos con 200 y la lista
        } catch (IllegalArgumentException e) {
            // Si el usuario no se encuentra
            return ResponseEntity.badRequest().body(null); // Respuesta con error
        }
    }

    /**
     * Buscar un reclamo por ID
     * @param idReclamo ID del reclamo
     * @return Reclamo encontrado
     */
    @GetMapping("/buscar/{idReclamo}")
    public ResponseEntity<Reclamo> buscarReclamoPorId(@PathVariable int idReclamo) {
        Reclamo reclamo = reclamoService.buscarReclamoPorId(idReclamo);
        if (reclamo != null) {
            return ResponseEntity.ok(reclamo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Actualizar un reclamo
     * @param idReclamo ID del reclamo
     * @param descripcion Nueva descripción del reclamo
     * @return Reclamo actualizado
     */
    @PutMapping("/actualizar/{idReclamo}")
    public ResponseEntity<Reclamo> actualizarReclamo(
            @PathVariable int idReclamo, 
            @RequestParam String descripcion) {
        Reclamo reclamoActualizado = reclamoService.actualizarReclamo(idReclamo, descripcion);
        if (reclamoActualizado != null) {
            return ResponseEntity.ok(reclamoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Eliminar un reclamo
     * @param idReclamo ID del reclamo a eliminar
     * @return Respuesta HTTP
     */
    @DeleteMapping("/eliminar/{idReclamo}")
    public ResponseEntity<Void> eliminarReclamo(@PathVariable int idReclamo) {
        Reclamo reclamo = reclamoService.buscarReclamoPorId(idReclamo);
        if (reclamo != null) {
            reclamoService.eliminarReclamo(idReclamo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
