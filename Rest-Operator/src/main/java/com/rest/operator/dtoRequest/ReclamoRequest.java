package com.rest.operator.dtoRequest;

import com.rest.operator.dto.UsuarioDto;

public class ReclamoRequest {
    private UsuarioDto usuario; // UsuarioDTO
    private String descripcionReclamo; // Descripción del reclamo
    private String fechaReclamo; // Fecha del reclamo (puedes usar Date o String)}
    private String importancia;
    

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
    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }
}
