package com.rest.operator.dto;

import java.time.LocalDate;

public class OperacionDTO {

    private int idOperacion;
    private String descripcion;
    private LocalDate fechaRecepcion;

    // Campos adicionales del Reclamo
    private int idReclamo;
    private int codUsuario;
    private int dniUsuario;
    private String nombreUsuario;
    private LocalDate fechaReclamo;
    private String nombreImportancia;

    // Campos del Operador
    private String nombreOperador;
    private int dniOperador;

    // Campos de la Especialidad
    private String nombreEspecialidad;

    // Constructor
    public OperacionDTO() {
        super();
    }

    // Constructor con todos los campos
    public OperacionDTO(int idOperacion, String descripcion, LocalDate fechaRecepcion, String nombreOperador,
                         int dniOperador, String nombreEspecialidad, int idReclamo, int codUsuario, int dniUsuario,
                         String nombreUsuario, LocalDate fechaReclamo, String nombreImportancia) {
        super();
        this.idOperacion = idOperacion;
        this.descripcion = descripcion;
        this.fechaRecepcion = fechaRecepcion;
        this.nombreOperador = nombreOperador;
        this.dniOperador = dniOperador;
        this.nombreEspecialidad = nombreEspecialidad;
        this.idReclamo = idReclamo;
        this.codUsuario = codUsuario;
        this.dniUsuario = dniUsuario;
        this.nombreUsuario = nombreUsuario;
        this.fechaReclamo = fechaReclamo;
        this.nombreImportancia = nombreImportancia;
    }

    // Getters y Setters
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
