package com.rest.service.dtoRequest;

import com.rest.service.dto.UsuarioDto;

public class ReclamoRequest {
    private UsuarioDto usuario; // UsuarioDTO para la información del usuario
    private String descripcionReclamo; // Descripción del reclamo
    private int idImportancia; // ID del tipo de importancia
    private String nombreImportancia; // Campo tipo para indicar si va a soporte o a desarrolladores

    // Getters y setters
    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public String getNombreImportancia() {
		return nombreImportancia;
	}

	public void setNombreImportancia(String nombreImportancia) {
		this.nombreImportancia = nombreImportancia;
	}

	public String getDescripcionReclamo() {
        return descripcionReclamo;
    }

    public void setDescripcionReclamo(String descripcionReclamo) {
        this.descripcionReclamo = descripcionReclamo;
    }

    public int getIdImportancia() {
        return idImportancia;
    }

    public void setIdImportancia(int idImportancia) {
        this.idImportancia = idImportancia;
    }

    
}
