package com.rest.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.service.model.Importancia;

public interface ImportanciaRepository extends JpaRepository<Importancia, Integer> {
	Optional<Importancia> findByNombreImportancia(String nombreImportancia);

}
