package com.rest.service.dtoRequest;

import com.rest.service.dto.UsuarioDto;

public class ReclamoRequest {
    private UsuarioDto usuario; // UsuarioDTO
    private String descripcionReclamo; // Descripción del reclamo
    private String fechaReclamo; // Fecha del reclamo (puedes usar Date o String)

    // Getters y setters
    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public String getDescripcionReclamo() {
        return descripcionReclamo;
    }

    public void setDescripcionReclamo(String descripcionReclamo) {
        this.descripcionReclamo = descripcionReclamo;
    }

    public String getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(String fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }
}
