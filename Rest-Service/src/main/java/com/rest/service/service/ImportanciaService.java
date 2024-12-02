package com.rest.service.service;

import com.rest.service.model.Importancia;
import com.rest.service.repository.ImportanciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportanciaService {

    @Autowired
    private ImportanciaRepository tipoRepository;

    // Obtener un tipo por su ID
    public Importancia obtenerImportanciaPorId(int id) {
        return tipoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de importancia no encontrado con ID: " + id));
    }

    // Listar todos los tipos de importancia
    public List<Importancia> listarImportancia() {
        return tipoRepository.findAll();
    }

    // Crear o actualizar un tipo
    public Importancia crearImportancia(Importancia importancia) {
        return tipoRepository.save(importancia);
    }

    // Eliminar un tipo por su ID
    public void eliminarImportancia(int id) {
        if (!tipoRepository.existsById(id)) {
            throw new IllegalArgumentException("Tipo de importancia no encontrado con ID: " + id);
        }
        tipoRepository.deleteById(id);
    }
}
