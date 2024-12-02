package com.rest.operator.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.operator.model.Especialidad;
import com.rest.operator.service.EspecialidadService;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    // Obtener todas las especialidades
    @GetMapping("/ListarEspecialidad")
    public ResponseEntity<List<Especialidad>> obtenerTodasLasEspecialidades() {
        List<Especialidad> especialidades = especialidadService.obtenerTodasLasEspecialidades();
        return new ResponseEntity<>(especialidades, HttpStatus.OK);
    }

    // Obtener una especialidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> obtenerEspecialidadPorId(@PathVariable int id) {
        Optional<Especialidad> especialidad = especialidadService.obtenerEspecialidadPorId(id);
        if (especialidad.isPresent()) {
            return new ResponseEntity<>(especialidad.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear una nueva especialidad
    @PostMapping("/CrearEspecialidad")
    public ResponseEntity<Especialidad> crearEspecialidad(@RequestBody Especialidad especialidad) {
        Especialidad nuevaEspecialidad = especialidadService.crearEspecialidad(especialidad);
        return new ResponseEntity<>(nuevaEspecialidad, HttpStatus.CREATED);
    }

    // Actualizar una especialidad existente
    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> actualizarEspecialidad(@PathVariable int id, @RequestBody Especialidad especialidad) {
        try {
            Especialidad especialidadActualizada = especialidadService.actualizarEspecialidad(id, especialidad);
            return new ResponseEntity<>(especialidadActualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una especialidad por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEspecialidad(@PathVariable int id) {
        try {
            especialidadService.eliminarEspecialidad(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
