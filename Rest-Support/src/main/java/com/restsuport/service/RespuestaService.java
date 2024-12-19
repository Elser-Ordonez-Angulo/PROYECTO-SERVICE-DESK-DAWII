package com.restsuport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restsuport.dto.OperacionDTO;
import com.restsuport.feingClient.OperacionClient;
import com.restsuport.model.Developer;
import com.restsuport.model.TechnicalSupport;
import com.restsuport.repository.IDeveloperRepository;
import com.restsuport.repository.ITechnicalSupportRepository;

import java.util.Optional;

@Service
public class RespuestaService {

    @Autowired
    private OperacionClient operacionClient;

    @Autowired
    private IDeveloperRepository developerRepository;

    @Autowired
    private ITechnicalSupportRepository technicalSupportRepository;

    // Crear respuesta y asociarla con la operación desde Rest-Operator
    public String crearRespuesta(String descripcionRespuesta, int idOperacion, String tipoEspecialista, int idEspecialista) {
        // Obtener operación desde Rest-Operator utilizando Feign Client
        OperacionDTO operacionDTO = operacionClient.getOperacionById(idOperacion);

        if (operacionDTO == null) {
            throw new RuntimeException("Operación no encontrada para el id: " + idOperacion);
        }

        // Crear la respuesta dependiendo del tipo de especialista
        switch (tipoEspecialista.toLowerCase()) {
            case "developer":
                Optional<Developer> developer = developerRepository.findById(idEspecialista);
                if (developer.isPresent()) {
                    Developer dev = developer.get();
                    dev.setRespuesta(descripcionRespuesta);
                    developerRepository.save(dev);
                    return "Respuesta de Developer guardada";
                } else {
                    return "Developer no encontrado";
                }

            case "technicalsupport":
                Optional<TechnicalSupport> technicalSupport = technicalSupportRepository.findById(idEspecialista);
                if (technicalSupport.isPresent()) {
                    TechnicalSupport techSupport = technicalSupport.get();
                    techSupport.setRespuesta(descripcionRespuesta);
                    technicalSupportRepository.save(techSupport);
                    return "Respuesta de Technical Support guardada";
                } else {
                    return "Technical Support no encontrado";
                }

            default:
                return "Tipo de especialista no reconocido";
        }
    }

    // Listar todas las operaciones
    public Iterable<OperacionDTO> listarOperaciones() {
        return operacionClient.getAllOperaciones();
    }
}
