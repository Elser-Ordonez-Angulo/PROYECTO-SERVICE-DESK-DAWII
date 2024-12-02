package com.rest.service.controller;

import com.rest.service.model.Importancia;
import com.rest.service.service.ImportanciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/importancia")
public class ImportanciaController {

    @Autowired
    private ImportanciaService importanciaService;

    // Endpoint para obtener un tipo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Importancia> obtenerImportanciaPorId(@PathVariable int id) {
        try {
            Importancia importancia = importanciaService.obtenerImportanciaPorId(id);
            return ResponseEntity.ok(importancia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint para listar todos los tipos
    @GetMapping
    public ResponseEntity<List<Importancia>> listarImportancia() {
        List<Importancia> importancias = importanciaService.listarImportancia();
        return ResponseEntity.ok(importancias);
    }

    // Endpoint para crear un nuevo tipo
    @PostMapping("/crearImportancia")
    public ResponseEntity<Importancia> crearImportancia(@RequestBody Importancia importancia) {
        Importancia nuevaImportancia = importanciaService.crearImportancia(importancia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaImportancia);
    }

    // Endpoint para actualizar un tipo existente
    @PutMapping("/{id}")
    public ResponseEntity<Importancia> actualizarImportancia(@PathVariable int id, @RequestBody Importancia importanciaActualizada) {
        try {
            Importancia importanciaExistente = importanciaService.obtenerImportanciaPorId(id);
            importanciaExistente.setNombreImportancia(importanciaActualizada.getNombreImportancia());
            Importancia importanciaGuardada = importanciaService.crearImportancia(importanciaExistente);
            return ResponseEntity.ok(importanciaGuardada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint para eliminar un tipo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImportancia(@PathVariable int id) {
        try {
            importanciaService.eliminarImportancia(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
