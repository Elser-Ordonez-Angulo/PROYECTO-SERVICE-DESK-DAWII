package com.rest.user.entity;

import java.time.LocalDateTime;



public class Reclamo {
	
    private String mensaje;
    private String categoria;
    private String estado;
    private LocalDateTime fechaHora;
	public Reclamo() {
		super();
	}
	public Reclamo(String mensaje, String categoria, String estado, LocalDateTime fechaHora) {
		super();
		this.mensaje = mensaje;
		this.categoria = categoria;
		this.estado = estado;
		this.fechaHora = fechaHora;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
    
    
}
