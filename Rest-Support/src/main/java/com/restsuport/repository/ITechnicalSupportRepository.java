package com.restsuport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restsuport.model.TechnicalSupport;

public interface ITechnicalSupportRepository extends JpaRepository<TechnicalSupport, Integer> {

}
