package com.rest.user.dto;

public class UsuarioDto {
    private int codUsuario;   // ID del usuario
    private int dniUsuario;   // DNI del usuario
    private String nombreUsuario; // Nombre del usuario

    // Constructor vacío
    public UsuarioDto() {}

    // Constructor completo
    public UsuarioDto(int codUsuario, int dniUsuario, String nombreUsuario) {
        this.codUsuario = codUsuario;
        this.dniUsuario = dniUsuario;
        this.nombreUsuario = nombreUsuario;
    }

    // Getters y Setters
    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public int getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(int dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
