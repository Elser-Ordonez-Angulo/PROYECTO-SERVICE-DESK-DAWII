package com.rest.operator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.operator.model.Operacion;


public interface IOperacionRepository extends JpaRepository<Operacion, Integer>{

}
