package com.rest.operator.service;

import com.rest.operator.dto.OperacionDTO;
import com.rest.operator.dto.ReclamoResponseDto;
import com.rest.operator.feingclient.OperatorFeignService;
import com.rest.operator.model.Operacion;
import com.rest.operator.model.Operadores;
import com.rest.operator.model.Especialidad;
import com.rest.operator.repository.IOperacionRepository;
import com.rest.operator.service.OperadorService;
import com.rest.operator.service.EspecialidadService;
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
    private OperatorFeignService serviceClient;  // Servicio para obtener detalles de reclamo

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
        nuevaOperacion.setFechaRecepcion(reclamo.getFechaReclamo()); // Usamos la fecha del reclamo
        nuevaOperacion.setOperador(operador);
        nuevaOperacion.setEspecialidad(especialidad);
        nuevaOperacion.setCodreclamo(reclamo.getIdReclamo()); // Asignar el id del reclamo

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
            // Obtener el detalle del reclamo utilizando el codreclamo de la operación
            ReclamoResponseDto reclamoDto = serviceClient.obtenerReclamoPorId(operacion.getCodreclamo());

            // Crear el DTO de la operación
            OperacionDTO dto = new OperacionDTO();
            dto.setIdOperacion(operacion.getIdOperacion());
            dto.setDescripcion(operacion.getDescripcion());
            dto.setFechaRecepcion(operacion.getFechaRecepcion());
            dto.setNombreOperador(operacion.getOperador().getNombreOperador());
            dto.setDniOperador(operacion.getOperador().getDniOperador());
            dto.setNombreEspecialidad(operacion.getEspecialidad().getNombreEspecialidad());

            // Mapear los detalles del reclamo al DTO de operación
            dto.setIdReclamo(reclamoDto.getIdReclamo());
            dto.setCodUsuario(reclamoDto.getCodUsuario());
            dto.setDniUsuario(reclamoDto.getDniUsuario());
            dto.setNombreUsuario(reclamoDto.getNombreUsuario());
            dto.setFechaReclamo(reclamoDto.getFechaReclamo());
            dto.setNombreImportancia(reclamoDto.getNombreImportancia());

            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * Obtener una operación por su ID
     */
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
            ReclamoResponseDto reclamo = operatorFeignService.obtenerReclamoPorId(operacion.getCodreclamo());
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

    /**
     * Actualizar una operación existente
     */
    @Transactional
    public OperacionDTO actualizarOperacion(int idOperacion, String descripcion, int idOperador, int idEspecialidad) {
        Optional<Operacion> operacionOptional = operacionRepository.findById(idOperacion);
        if (!operacionOptional.isPresent()) {
            throw new RuntimeException("Operacion no encontrada con ID: " + idOperacion);
        }

        Operacion operacionExistente = operacionOptional.get();

        // Buscar el operador y la especialidad desde sus respectivos servicios
        Operadores operador = operadorService.obtenerOperadorPorId(idOperador)
            .orElseThrow(() -> new RuntimeException("Operador no encontrado con ID: " + idOperador));
        Especialidad especialidad = especialidadService.obtenerEspecialidadPorId(idEspecialidad)
            .orElseThrow(() -> new RuntimeException("Especialidad no encontrada con ID: " + idEspecialidad));

        // Actualizar los campos de la operación
        operacionExistente.setDescripcion(descripcion);
        operacionExistente.setOperador(operador);
        operacionExistente.setEspecialidad(especialidad);

        // Guardar la operación actualizada
        Operacion operacionActualizada = operacionRepository.save(operacionExistente);

        // Convertir a DTO y devolver la respuesta
        OperacionDTO operacionDTO = new OperacionDTO();
        operacionDTO.setIdOperacion(operacionActualizada.getIdOperacion());
        operacionDTO.setDescripcion(operacionActualizada.getDescripcion());
        operacionDTO.setFechaRecepcion(operacionActualizada.getFechaRecepcion());
        operacionDTO.setNombreOperador(operador.getNombreOperador());
        operacionDTO.setDniOperador(operador.getDniOperador());
        operacionDTO.setNombreEspecialidad(especialidad.getNombreEspecialidad());

        return operacionDTO;
    }

    /**
     * Eliminar una operación por su ID
     */
    @Transactional
    public void eliminarOperacion(int idOperacion) {
        Optional<Operacion> operacionOptional = operacionRepository.findById(idOperacion);
        if (!operacionOptional.isPresent()) {
            throw new RuntimeException("Operacion no encontrada con ID: " + idOperacion);
        }

        // Eliminar la operación
        operacionRepository.deleteById(idOperacion);
    }
}
