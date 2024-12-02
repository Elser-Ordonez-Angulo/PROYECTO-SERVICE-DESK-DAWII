package com.rest.operator.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.operator.model.Operadores;
import com.rest.operator.service.OperadorService;

@RestController
@RequestMapping("/api/operadores")
public class OperadorController {

    @Autowired
    private OperadorService operadorService;

    // Obtener todos los operadores
    @GetMapping("/ListarOperadores")
    public ResponseEntity<List<Operadores>> obtenerTodosOperadores() {
        List<Operadores> operadores = operadorService.obtenerTodosOperadores();
        return new ResponseEntity<>(operadores, HttpStatus.OK);
    }

    // Obtener un operador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Operadores> obtenerOperadorPorId(@PathVariable int id) {
        Optional<Operadores> operador = operadorService.obtenerOperadorPorId(id);
        if (operador.isPresent()) {
            return new ResponseEntity<>(operador.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo operador
    @PostMapping("/CrearOperador")
    public ResponseEntity<Operadores> crearOperador(@RequestBody Operadores operador) {
        Operadores nuevoOperador = operadorService.crearOperador(operador);
        return new ResponseEntity<>(nuevoOperador, HttpStatus.CREATED);
    }

    // Actualizar un operador existente
    @PutMapping("/{id}")
    public ResponseEntity<Operadores> actualizarOperador(@PathVariable int id, @RequestBody Operadores operador) {
        try {
            Operadores operadorActualizado = operadorService.actualizarOperador(id, operador);
            return new ResponseEntity<>(operadorActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un operador por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOperador(@PathVariable int id) {
        try {
            operadorService.eliminarOperador(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
