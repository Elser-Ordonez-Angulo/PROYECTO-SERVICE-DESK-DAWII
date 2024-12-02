package com.rest.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.user.dto.UsuarioDto;
import com.rest.user.model.Usuario;
import com.rest.user.service.UsuarioService;

@RequestMapping("api/usuario")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioService uService;

    // LISTAR TODOS LOS USUARIOS
    @GetMapping("/listar/Usuario")
    public List<UsuarioDto> listarUsuario() {
        // Convertir lista de Usuarios a UsuarioDto
        return uService.listarUsuario().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // BUSCAR USUARIO POR ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = uService.buscarUsuario(id);
        if (usuario != null) {
            return new ResponseEntity<>(convertToDto(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // BUSCAR USUARIO POR DNI
    @GetMapping("/buscar/dni/{dniUsuario}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorDni(@PathVariable int dniUsuario) {
        Usuario usuario = uService.buscarUsuarioPorDni(dniUsuario);
        if (usuario != null) {
            return new ResponseEntity<>(convertToDto(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // CREAR NUEVO USUARIO
    @PostMapping("/crear/Usuario")
    public ResponseEntity<Void> crearUsuario(@RequestBody Usuario usuario) {
        uService.crearUsuario(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // ACTUALIZAR USUARIO POR DNI
    @PutMapping("/actualizar/dni/{dniUsuario}")
    public ResponseEntity<UsuarioDto> actualizarUsuarioPorDni(
            @PathVariable int dniUsuario,
            @RequestBody Usuario usuarioActualizado) {

        Usuario usuario = uService.actualizarUsuarioPorDni(dniUsuario, usuarioActualizado);
        if (usuario != null) {
            return new ResponseEntity<>(convertToDto(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ELIMINAR USUARIO POR DNI
    @DeleteMapping("/borrar/dni/{dniUsuario}")
    public ResponseEntity<Void> borrarUsuarioPorDni(@PathVariable int dniUsuario) {
        Usuario usuario = uService.buscarUsuarioPorDni(dniUsuario);
        if (usuario != null) {
            uService.borrarUsuarioPorDni(dniUsuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // MÉTODO PRIVADO PARA CONVERTIR ENTIDAD A DTO
    private UsuarioDto convertToDto(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setCodUsuario(usuario.getCodUsuario());
        dto.setDniUsuario(usuario.getDniUsuario());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        return dto;
    }
}
