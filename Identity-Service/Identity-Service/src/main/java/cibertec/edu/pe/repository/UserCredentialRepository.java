package cibertec.edu.pe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.edu.pe.entity.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
	
	Optional<UserCredential> findByname (String username);
	

}
