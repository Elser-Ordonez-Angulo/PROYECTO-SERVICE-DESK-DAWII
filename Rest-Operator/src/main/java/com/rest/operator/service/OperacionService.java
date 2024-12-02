

package com.rest.operator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.operator.dto.OperacionDTO;
import com.rest.operator.model.Operacion;
import com.rest.operator.repository.IOperacionRepository;
import java.util.stream.Collectors;

@Service
public class OperacionService {

    @Autowired
    private IOperacionRepository operacionRepository;

    public List<OperacionDTO> obtenerOperacionesConDetalles() {
        List<Operacion> operaciones = operacionRepository.findAll();

        return operaciones.stream().map(operacion -> {
            OperacionDTO dto = new OperacionDTO();
            dto.setIdOperacion(operacion.getIdOperacion());
            dto.setDescripcion(operacion.getDescripcion());
            dto.setFechaRecepcion(operacion.getFechaRecepcion());

            // Datos del Operador
            dto.setNombreOperador(operacion.getOperador().getNombreOperador());
            dto.setDniOperador(operacion.getOperador().getDniOperador());

            // Datos de la Especialidad
            dto.setNombreEspecialidad(operacion.getEspecialidad().getNombreEspecialidad());

            return dto;
        }).collect(Collectors.toList());
    }
}
