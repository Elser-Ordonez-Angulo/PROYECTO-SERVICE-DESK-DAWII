package com.restsuport.service;

import com.restsuport.dto.OperacionDTO;
import com.restsuport.feingClient.OperacionClient;
import com.restsuport.model.Respuesta;
import com.restsuport.repository.IRespuestaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RespuestaService {

    @Autowired
    private OperacionClient operacionClient;

    @Autowired
    private IRespuestaRepository respuestaRepository;

    /**
     * Método para crear una respuesta asociada a una operación.
     * @param descripcionRespuesta La descripción de la respuesta.
     * @param idOperacion El ID de la operación asociada.
     * @return La respuesta creada.
     */
    public Respuesta crearRespuesta(String descripcionRespuesta, int idOperacion) {
        // Llamar al servicio del microservicio Rest-Operator para obtener la operación
        OperacionDTO operacionDTO = operacionClient.getOperacionById(idOperacion);

        // Validar si la operación existe
        if (operacionDTO == null) {
            throw new RuntimeException("Operación no encontrada para el id: " + idOperacion);
        }

        // Crear la respuesta con la operación obtenida
        Respuesta respuesta = new Respuesta(
                descripcionRespuesta,
                LocalDate.now(),
                operacionDTO.getIdOperacion(),
                operacionDTO.getDescripcion()
        );

        // Guardar la respuesta en la base de datos
        return respuestaRepository.save(respuesta);
    }

    /**
     * Obtener todas las respuestas almacenadas.
     * @return Una lista de respuestas.
     */
    public Iterable<Respuesta> obtenerTodasLasRespuestas() {
        return respuestaRepository.findAll();
    }

    /**
     * Obtener una respuesta por su ID.
     * @param idRespuesta El ID de la respuesta.
     * @return La respuesta solicitada.
     */
    public Respuesta obtenerRespuestaPorId(int idRespuesta) {
        return respuestaRepository.findById(idRespuesta).orElse(null);
    }
}
