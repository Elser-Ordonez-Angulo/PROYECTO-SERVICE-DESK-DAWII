package com.rest.service.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.service.dto.UsuarioDto;
import com.rest.service.dtoRequest.ReclamoRequest;
import com.rest.service.feingclient.UsuarioFeignClient;
import com.rest.service.model.Importancia;
import com.rest.service.model.Reclamo;
import com.rest.service.repository.ReclamoRepository;
import com.rest.service.responseDto.ReclamoResponseDto;
import com.rest.service.repository.ImportanciaRepository;

@Service
public class ReclamoService {

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;

    @Autowired
    private ReclamoRepository reclamoRepository;
    
    @Autowired
    private ImportanciaRepository tipoRepository;

    /**
     * Crear un nuevo reclamo
     * @param dniUsuario DNI del usuario
     * @param descripcion Descripción del reclamo
     * @return Reclamo creado
     */
    public Reclamo crearReclamo(ReclamoRequest reclamoRequest) {
        // Obtener los datos del usuario desde el DTO
        UsuarioDto usuarioDto = reclamoRequest.getUsuario();

        // Validar que el usuario existe por DNI a través de Feign Client
        UsuarioDto usuario = usuarioFeignClient.obtenerUsuarioPorDni(usuarioDto.getDniUsuario());
        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no encontrado con el DNI: " + usuarioDto.getDniUsuario());
        }

        // Validar que el codUsuario existe y coincide con el obtenido
        if (usuario.getCodUsuario() != usuarioDto.getCodUsuario()) {
            throw new IllegalArgumentException("Usuario no registrado con el CodUsuario: " + usuarioDto.getCodUsuario());
        }

        // Obtener el tipo de importancia desde el repositorio
        Importancia importancia = tipoRepository.findById(reclamoRequest.getIdImportancia())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Tipo de importancia no encontrado con ID: " + reclamoRequest.getIdImportancia()));

        // Crear la instancia del reclamo
        Reclamo nuevoReclamo = new Reclamo();
        nuevoReclamo.setCodUsuario(usuario.getCodUsuario()); // Asignar codUsuario
        nuevoReclamo.setDniUsuario(usuario.getDniUsuario()); // Asignar dniUsuario
        nuevoReclamo.setNombreUsuario(usuario.getNombreUsuario()); // Asignar nombreUsuario
        nuevoReclamo.setDescripcion(reclamoRequest.getDescripcionReclamo()); // Asignar descripción del reclamo
        nuevoReclamo.setFechaReclamo(LocalDate.now()); // Asignar la fecha actual automáticamente

        // Asignar el tipo de importancia al reclamo
        nuevoReclamo.setImportancia(importancia);

        // Asignar el nombre de la importancia al campo nombreImportancia
        nuevoReclamo.setNombreImportancia(importancia.getNombreImportancia());

        // Guardar el reclamo en la base de datos
        return reclamoRepository.save(nuevoReclamo);
    }



    /**
     * Listar todos los reclamos
     * @return Lista de reclamos
     */
    public List<ReclamoResponseDto> listarReclamos() {
        List<Reclamo> reclamos = reclamoRepository.findAll();
        List<ReclamoResponseDto> responseDtos = new ArrayList<>();

        for (Reclamo reclamo : reclamos) {
            // Mapeamos la entidad Reclamo a ReclamoResponseDto
            ReclamoResponseDto dto = new ReclamoResponseDto(
                reclamo.getIdReclamo(),
                reclamo.getCodUsuario(),
                reclamo.getDniUsuario(),
                reclamo.getNombreUsuario(),
                reclamo.getDescripcion(),
                reclamo.getFechaReclamo(),
                reclamo.getImportancia().getNombreImportancia()  // Aquí asignamos el nombre de la importancia
            );
            responseDtos.add(dto);
        }

        return responseDtos;
    }

    //listar reclamos por dni
    public List<Reclamo> listarReclamosPorDni(int dniUsuario) {
        // Obtener los datos del usuario desde el microservicio Rest-User
        UsuarioDto usuarioDto = usuarioFeignClient.obtenerUsuarioPorDni(dniUsuario);

        // Validar si el usuario existe
        if (usuarioDto == null) {
            throw new IllegalArgumentException("Usuario no encontrado con el DNI: " + dniUsuario);
        }

        // Obtener los reclamos asociados al usuario
        return reclamoRepository.findByDniUsuario(dniUsuario);
    }

    /**
     * Buscar un reclamo por ID
     * @param idReclamo ID del reclamo
     * @return Reclamo encontrado
     */
    public ReclamoResponseDto buscarReclamoPorId(int idReclamo) {
        // Buscar el reclamo por ID
        Optional<Reclamo> reclamoOptional = reclamoRepository.findById(idReclamo);

        // Si el reclamo no existe, retornamos null
        if (!reclamoOptional.isPresent()) {
            return null; // O puedes lanzar una excepción si prefieres
        }

        // Obtener el reclamo
        Reclamo reclamo = reclamoOptional.get();

        // Mapeamos la entidad Reclamo a ReclamoResponseDto
        ReclamoResponseDto dto = new ReclamoResponseDto(
            reclamo.getIdReclamo(),
            reclamo.getCodUsuario(),
            reclamo.getDniUsuario(),
            reclamo.getNombreUsuario(),
            reclamo.getDescripcion(),
            reclamo.getFechaReclamo(),
            reclamo.getImportancia().getNombreImportancia()  // Aquí asignamos el nombre de la importancia
        );

        return dto;
    }


    /**
     * Actualizar un reclamo
     * @param idReclamo ID del reclamo
     * @param descripcion Nueva descripción del reclamo
     * @return Reclamo actualizado
     */
    public Reclamo actualizarReclamo(int idReclamo, String descripcion) {
        Reclamo reclamoExistente = reclamoRepository.findById(idReclamo).orElse(null);
        if (reclamoExistente != null) {
            reclamoExistente.setDescripcion(descripcion);
            return reclamoRepository.save(reclamoExistente);
        }
        return null;
    }

    /**
     * Eliminar un reclamo
     * @param idReclamo ID del reclamo a eliminar
     */
    public Reclamo eliminarReclamo(int idReclamo) {
        Optional<Reclamo> reclamoOptional = reclamoRepository.findById(idReclamo);
        if (reclamoOptional.isPresent()) {
            Reclamo reclamo = reclamoOptional.get();
            reclamoRepository.delete(reclamo);  // Elimina el reclamo de la base de datos
            return reclamo;  // Devuelve el reclamo eliminado
        } else {
            return null;  // Si no se encuentra, retorna null
        }
    }

}
