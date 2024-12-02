package com.rest.service.responseDto;

import java.time.LocalDate;

public class ReclamoResponseDto {
    private int idReclamo;
    private int codUsuario;
    private int dniUsuario;
    private String nombreUsuario;
    private String descripcion;
    private LocalDate fechaReclamo;
    private String nombreImportancia;  // Este campo contendrá el nombre de la importancia

    public ReclamoResponseDto(int idReclamo, int codUsuario, int dniUsuario, String nombreUsuario, 
                              String descripcion, LocalDate fechaReclamo, String nombreImportancia) {
        this.idReclamo = idReclamo;
        this.codUsuario = codUsuario;
        this.dniUsuario = dniUsuario;
        this.nombreUsuario = nombreUsuario;
        this.descripcion = descripcion;
        this.fechaReclamo = fechaReclamo;
        this.nombreImportancia = nombreImportancia;
    }

    // Getters y Setters
    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(LocalDate fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }

    public String getNombreImportancia() {
        return nombreImportancia;
    }

    public void setNombreImportancia(String nombreImportancia) {
        this.nombreImportancia = nombreImportancia;
    }
}

