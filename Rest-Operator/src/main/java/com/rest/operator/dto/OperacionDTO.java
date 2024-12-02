package com.rest.operator.dto;

import java.time.LocalDate;

public class OperacionDTO {
	
	 private int idOperacion;
	    private String descripcion;
	    private LocalDate fechaRecepcion;
///////////////////////////////////////
	    private String nombreOperador;
	    private int dniOperador;
///////////////
	    private String nombreEspecialidad;
public OperacionDTO() {
	super();
}
public OperacionDTO(int idOperacion, String descripcion, LocalDate fechaRecepcion, String nombreOperador,
		int dniOperador, String nombreEspecialidad) {
	super();
	this.idOperacion = idOperacion;
	this.descripcion = descripcion;
	this.fechaRecepcion = fechaRecepcion;
	this.nombreOperador = nombreOperador;
	this.dniOperador = dniOperador;
	this.nombreEspecialidad = nombreEspecialidad;
}
public int getIdOperacion() {
	return idOperacion;
}
public void setIdOperacion(int idOperacion) {
	this.idOperacion = idOperacion;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public LocalDate getFechaRecepcion() {
	return fechaRecepcion;
}
public void setFechaRecepcion(LocalDate fechaRecepcion) {
	this.fechaRecepcion = fechaRecepcion;
}
public String getNombreOperador() {
	return nombreOperador;
}
public void setNombreOperador(String nombreOperador) {
	this.nombreOperador = nombreOperador;
}
public int getDniOperador() {
	return dniOperador;
}
public void setDniOperador(int dniOperador) {
	this.dniOperador = dniOperador;
}
public String getNombreEspecialidad() {
	return nombreEspecialidad;
}
public void setNombreEspecialidad(String nombreEspecialidad) {
	this.nombreEspecialidad = nombreEspecialidad;
}
	    
	    
	    
	    
	    
	    
	    

}
