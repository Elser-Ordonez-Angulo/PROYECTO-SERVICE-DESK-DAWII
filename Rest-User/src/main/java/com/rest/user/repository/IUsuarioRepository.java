package com.rest.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.user.model.Usuario;



public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	// Buscar un usuario por su DNI
    Optional<Usuario> findByDniUsuario(int dniUsuario);

 // Método para eliminar por DNI
    void deleteByDniUsuario(int dniUsuario);

}
