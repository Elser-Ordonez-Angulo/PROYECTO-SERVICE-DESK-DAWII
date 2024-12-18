package com.rest.operator.service;

import com.rest.operator.feingclient.OperatorFeignService;
import com.rest.operator.dto.OperacionDTO;
import com.rest.operator.dto.ReclamoResponseDto;
import com.rest.operator.dtoRequest.ReclamoRequest;
import com.rest.operator.model.Operacion;
import com.rest.operator.repository.IOperacionRepository;
import com.rest.operator.model.Operadores;
import com.rest.operator.model.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperacionService {

    @Autowired
    private IOperacionRepository operacionRepository;

    @Autowired
    private OperatorFeignService operatorFeignService;

    @Autowired
    private OperadorService operadorService;  // Servicio para obtener Operador
    @Autowired
    private EspecialidadService especialidadService;  // Servicio para obtener Especialidad
    @Autowired
    private OperatorFeignService serviceClient;  // Servicio para obtener Especialidad

    /**
     * Crear una nueva operación asociando los datos del reclamo
     */
    @Transactional
    public OperacionDTO crearOperacionConReclamo(int idReclamo, String descripcion, int idOperador, int idEspecialidad) {
        // Obtener los datos del reclamo desde el microservicio 'service'
        ReclamoResponseDto reclamo = operatorFeignService.obtenerReclamoPorId(idReclamo);

        // Buscar el operador y la especialidad desde sus respectivos servicios
        Operadores operador = operadorService.obtenerOperadorPorId(idOperador)
            .orElseThrow(() -> new RuntimeException("Operador no encontrado con ID: " + idOperador));
        Especialidad especialidad = especialidadService.obtenerEspecialidadPorId(idEspecialidad)
            .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con ID: " + idEspecialidad));

        // Crear la nueva operación
        Operacion nuevaOperacion = new Operacion();
        nuevaOperacion.setDescripcion(descripcion);
        nuevaOperacion.setFechaRecepcion(reclamo.getFechaReclamo());
        nuevaOperacion.setOperador(operador);
        nuevaOperacion.setEspecialidad(especialidad);

        // Guardar la operación en la base de datos
        Operacion operacionGuardada = operacionRepository.save(nuevaOperacion);

        // Convertir a DTO y devolver la respuesta
        OperacionDTO operacionDTO = new OperacionDTO();
        operacionDTO.setIdOperacion(operacionGuardada.getIdOperacion());
        operacionDTO.setDescripcion(operacionGuardada.getDescripcion());
        operacionDTO.setFechaRecepcion(operacionGuardada.getFechaRecepcion());
        operacionDTO.setNombreOperador(operador.getNombreOperador());
        operacionDTO.setDniOperador(operador.getDniOperador());
        operacionDTO.setNombreEspecialidad(especialidad.getNombreEspecialidad());

        // Mapear los nuevos campos desde ReclamoResponseDto
        operacionDTO.setIdReclamo(reclamo.getIdReclamo());
        operacionDTO.setCodUsuario(reclamo.getCodUsuario());
        operacionDTO.setDniUsuario(reclamo.getDniUsuario());
        operacionDTO.setNombreUsuario(reclamo.getNombreUsuario());
        operacionDTO.setFechaReclamo(reclamo.getFechaReclamo());
        operacionDTO.setNombreImportancia(reclamo.getNombreImportancia());

        return operacionDTO;
    }

    /**
     * Obtener todas las operaciones con detalles adicionales
     */
    public List<OperacionDTO> obtenerOperacionesConDetalles() {
        List<Operacion> operaciones = operacionRepository.findAll();
        
        

        return operaciones.stream().map(operacion -> {
        	
        	int codreclamo = operacion.getCodreclamo();
        	ReclamoResponseDto reclamoDto=serviceClient.obtenerReclamoPorId(codreclamo);
        	
            OperacionDTO dto = new OperacionDTO();
            dto.setIdReclamo(reclamoDto.getIdReclamo());
            dto.setIdOperacion(operacion.getIdOperacion());
            dto.setDescripcion(operacion.getDescripcion());
            dto.setFechaRecepcion(operacion.getFechaRecepcion());
            dto.setNombreOperador(operacion.getOperador().getNombreOperador());
            dto.setDniOperador(operacion.getOperador().getDniOperador());
            dto.setNombreEspecialidad(operacion.getEspecialidad().getNombreEspecialidad());
            return dto;
        }).collect(Collectors.toList());
    }

   
    public OperacionDTO obtenerOperacionPorId(int idOperacion) {
        Optional<Operacion> operacionOptional = operacionRepository.findById(idOperacion);
        if (operacionOptional.isPresent()) {
            Operacion operacion = operacionOptional.get();
            OperacionDTO dto = new OperacionDTO();
            dto.setIdOperacion(operacion.getIdOperacion());
            dto.setDescripcion(operacion.getDescripcion());
            dto.setFechaRecepcion(operacion.getFechaRecepcion());
            dto.setNombreOperador(operacion.getOperador().getNombreOperador());
            dto.setDniOperador(operacion.getOperador().getDniOperador());
            dto.setNombreEspecialidad(operacion.getEspecialidad().getNombreEspecialidad());
            
            // Obtener detalles adicionales del reclamo
            ReclamoResponseDto reclamo = operatorFeignService.obtenerReclamoPorId(operacion.getIdOperacion()); // Aquí se podría modificar dependiendo de cómo se esté obteniendo el reclamo.
            dto.setIdReclamo(reclamo.getIdReclamo());
            dto.setCodUsuario(reclamo.getCodUsuario());
            dto.setDniUsuario(reclamo.getDniUsuario());
            dto.setNombreUsuario(reclamo.getNombreUsuario());
            dto.setFechaReclamo(reclamo.getFechaReclamo());
            dto.setNombreImportancia(reclamo.getNombreImportancia());

            return dto;
        } else {
            return null; // O lanzar una excepción personalizada si es necesario
        }
    }
}
