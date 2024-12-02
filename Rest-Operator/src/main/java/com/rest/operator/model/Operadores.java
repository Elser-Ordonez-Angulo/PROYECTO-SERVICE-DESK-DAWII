package com.rest.operator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_Operadores")


public class Operadores {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOperador;
	private int dniOperador;
	private String nombreOperador;
	private String correoOperador;
	private int telefonoOperador;
	public Operadores() {
		super();
	}
	public Operadores(int idOperador, int dniOperador, String nombreOperador, String correoOperador,
			int telefonoOperador) {
		super();
		this.idOperador = idOperador;
		this.dniOperador = dniOperador;
		this.nombreOperador = nombreOperador;
		this.correoOperador = correoOperador;
		this.telefonoOperador = telefonoOperador;
	}
	public int getIdOperador() {
		return idOperador;
	}
	public void setIdOperador(int idOperador) {
		this.idOperador = idOperador;
	}
	public int getDniOperador() {
		return dniOperador;
	}
	public void setDniOperador(int dniOperador) {
		this.dniOperador = dniOperador;
	}
	public String getNombreOperador() {
		return nombreOperador;
	}
	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}
	public String getCorreoOperador() {
		return correoOperador;
	}
	public void setCorreoOperador(String correoOperador) {
		this.correoOperador = correoOperador;
	}
	public int getTelefonoOperador() {
		return telefonoOperador;
	}
	public void setTelefonoOperador(int telefonoOperador) {
		this.telefonoOperador = telefonoOperador;
	}
	
	
	
	
	
	
	

}
