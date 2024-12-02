package com.rest.operator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.operator.model.Especialidad;
import com.rest.operator.repository.IEspecialidadRepository;


@Service
public class EspecialidadService {

    @Autowired
    private IEspecialidadRepository especialidadRepository;

    // Obtener todas las especialidades
    public List<Especialidad> obtenerTodasLasEspecialidades() {
        return especialidadRepository.findAll();
    }

    // Obtener una especialidad por ID
    public Optional<Especialidad> obtenerEspecialidadPorId(int id) {
        return especialidadRepository.findById(id);
    }

    // Crear una nueva especialidad
    public Especialidad crearEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    // Actualizar una especialidad existente
    public Especialidad actualizarEspecialidad(int id, Especialidad especialidadActualizada) {
        Optional<Especialidad> especialidadExistente = especialidadRepository.findById(id);
        if (especialidadExistente.isPresent()) {
            Especialidad especialidad = especialidadExistente.get();
            especialidad.setNombreEspecialidad(especialidadActualizada.getNombreEspecialidad());
            return especialidadRepository.save(especialidad);
        } else {
            throw new RuntimeException("Especialidad no encontrada con ID: " + id);
        }
    }

    // Eliminar una especialidad por ID
    public void eliminarEspecialidad(int id) {
        if (especialidadRepository.existsById(id)) {
            especialidadRepository.deleteById(id);
        } else {
            throw new RuntimeException("Especialidad no encontrada con ID: " + id);
        }
    }
}
