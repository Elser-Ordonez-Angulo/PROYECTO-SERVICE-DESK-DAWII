package com.rest.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.service.model.Reclamo;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
	 // Buscar reclamos por DNI de usuario
    List<Reclamo> findByDniUsuario(int dniUsuario);

}
