package com.restsuport.service;

import com.restsuport.dto.OperacionDTO;
import com.restsuport.model.Developer;
import com.restsuport.repository.IDeveloperRepository;
import com.restsuport.feingClient.OperacionClient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeveloperService {

    @Autowired
    private IDeveloperRepository developerRepository;

    @Autowired
    private OperacionClient operacionClient;

    /**
     * Registra la respuesta de un developer para una operación.
     * Valida que la operación con idOperacion exista antes de guardar la respuesta.
     *
     * @param developer Developer con datos de la respuesta y idOperacion
     * @return Developer guardado si idOperacion es válido, null si no es válido
     */
    @Transactional
    public Developer crearRespuesta(Developer developer) {
        // Validar si la operación existe a través del Feign Client
        OperacionDTO operacionDTO = operacionClient.getOperacionById(developer.getIdOperacion());
        
        if (operacionDTO == null) {
            // Si la operación no existe, lanzamos una excepción o retornamos null
            throw new IllegalArgumentException("Operación no encontrada con el ID: " + developer.getIdOperacion());
        }

        // Si la operación existe, guardar la respuesta del developer
        developer.setNombre(operacionDTO.getNombreOperador()); // Asignamos nombreOperador de la operación
        developer.setEspecialidad(operacionDTO.getNombreEspecialidad()); // Asignamos especialidad de la operación
        developer.setRespuesta("Respuesta de la operación: " + operacionDTO.getDescripcion()); // Asignamos respuesta (ejemplo)

        // Guardar el developer con la respuesta en la base de datos
        return developerRepository.save(developer);
    }
    public List<OperacionDTO> listarTodasOperaciones() {
        return operacionClient.getAllOperaciones();
    }
}
