package com.rest.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.user.entity.Reclamo;
import com.rest.user.model.Usuario;
import com.rest.user.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RequestMapping("api/usuario")
@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService uService;
	
	/////////////LISTAR USUARIO////////////////
	
	@GetMapping("/listar/Usuario")
	public List<Usuario> listarUsuario(){
		return uService.listarUsuario();
	}
	
	// Buscar usuario por ID (CodUsuario)
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario =uService.buscarUsuario(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

 // Buscar usuario por DNI
    @GetMapping("buscar/dni/{dniUsuario}")
    public ResponseEntity<Usuario> buscarUsuarioPorDni(@PathVariable int dniUsuario) {
        Usuario usuario = uService.buscarUsuarioPorDni(dniUsuario);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //////////CREAR USUARIO///////////////

	@PostMapping("/crear/Usuario")
	public void crearUsuario(@RequestBody Usuario usuario) {
		uService.crearUsuario(usuario);
	}
	
	@PostMapping("/createReclamo")
	public void crearReclamo(@RequestBody Reclamo reclamo) {
		uService.crearReclamo(reclamo);
	}
	
	
	
	/////////////////ACTUALIZAR USUARIO//////////////

    // Actualizar usuario por DNI
    @PutMapping("/actualizar/dni/{dniUsuario}")
    public ResponseEntity<Usuario> actualizarUsuarioPorDni(
            @PathVariable int dniUsuario, 
            @RequestBody Usuario usuarioActualizado) {

        Usuario usuario = uService.actualizarUsuarioPorDni(dniUsuario, usuarioActualizado);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Borrar usuario por DNI
    @DeleteMapping("/borrar/dni/{dniUsuario}")
    public ResponseEntity<Void> borrarUsuarioPorDni(@PathVariable int dniUsuario) {
        Usuario usuario = uService.buscarUsuarioPorDni(dniUsuario);
        if (usuario != null) {
            uService.borrarUsuarioPorDni(dniUsuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

}}
