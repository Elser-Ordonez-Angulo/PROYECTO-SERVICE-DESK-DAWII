package com.restsuport.service;

import com.restsuport.dto.OperacionDTO;
import com.restsuport.model.Developer;
import com.restsuport.repository.IDeveloperRepository;
import com.restsuport.feingClient.OperacionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     * @return Developer guardado si idOperacion es válido, error si no es válido
     */
    @Transactional
    public Developer crearRespuesta(Developer developer) {
        // Validar si la operación existe a través del Feign Client
        OperacionDTO operacionDTO = operacionClient.getOperacionById(developer.getIdOperacion());
        
        if (operacionDTO == null) {
            // Si la operación no existe, lanzamos una excepción
            throw new IllegalArgumentException("Operación no encontrada con el ID: " + developer.getIdOperacion());
        }

        // Si la operación existe, asignamos una respuesta fija
        developer.setNombre(operacionDTO.getNombreOperador()); // Asignamos nombreOperador de la operación
        developer.setEspecialidad(operacionDTO.getNombreEspecialidad()); // Asignamos especialidad de la operación
        developer.setRespuesta("Respuesta a la operación con descripción:" + operacionDTO.getDescripcion() + "." + " Es la siguiente: " + developer.getRespuesta()); // Asignamos respuesta fija

        // Guardar el developer con la respuesta en la base de datos
        return developerRepository.save(developer);
    }

    /**
     * Método para listar todas las operaciones.
     *
     * @return Lista de operaciones disponibles
     */
    public List<OperacionDTO> listarTodasOperaciones() {
        return operacionClient.getAllOperaciones();
    }

    /**
     * Método para eliminar una respuesta de un developer por su ID.
     *
     * @param id Id del Developer a eliminar
     * @throws IllegalArgumentException si el Developer con el ID no existe
     */
    @Transactional
    public void eliminarRespuesta(Integer id) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Developer no encontrado con el ID: " + id));

        // Eliminar la respuesta del developer
        developerRepository.delete(developer);
    }
}
