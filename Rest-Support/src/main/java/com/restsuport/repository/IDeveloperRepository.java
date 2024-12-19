package com.restsuport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restsuport.model.Developer;

public interface IDeveloperRepository extends JpaRepository<Developer, Integer> {

}
