package com.restsuport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restsuport.model.Developer;

public interface IDeveloperRepository extends JpaRepository<Developer, Integer> {

	Optional<Developer> findByNombre(String nombreOperador);

}
