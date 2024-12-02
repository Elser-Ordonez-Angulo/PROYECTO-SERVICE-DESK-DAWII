package com.rest.operator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.operator.model.Operadores;
import com.rest.operator.repository.IOperadorRepository;

@Service
public class OperadorService {
	
	 @Autowired
	    private IOperadorRepository operadorRepository;
	 
	// Obtener todos los operadores
	    public List<Operadores> obtenerTodosOperadores() {
	        return operadorRepository.findAll();
	    }

	    // Obtener un operador por ID
	    public Optional<Operadores> obtenerOperadorPorId(int id) {
	        return operadorRepository.findById(id);
	    }

	    // Crear un nuevo operador
	    public Operadores crearOperador(Operadores operador) {
	        return operadorRepository.save(operador);
	    }

	    // Actualizar un operador existente
	    public Operadores actualizarOperador(int id, Operadores operadorActualizado) {
	        Optional<Operadores> operadorExistente = operadorRepository.findById(id);
	        if (operadorExistente.isPresent()) {
	            Operadores operador = operadorExistente.get();
	            operador.setDniOperador(operadorActualizado.getDniOperador());
	            operador.setNombreOperador(operadorActualizado.getNombreOperador());
	            operador.setCorreoOperador(operadorActualizado.getCorreoOperador());
	            operador.setTelefonoOperador(operadorActualizado.getTelefonoOperador());
	            return operadorRepository.save(operador);
	        } else {
	            throw new RuntimeException("Operador no encontrado con ID: " + id);
	        }
	    }

	    // Eliminar un operador por ID
	    public void eliminarOperador(int id) {
	        if (operadorRepository.existsById(id)) {
	            operadorRepository.deleteById(id);
	        } else {
	            throw new RuntimeException("Operador no encontrado con ID: " + id);
	        }
	    }
	    
	    
}
