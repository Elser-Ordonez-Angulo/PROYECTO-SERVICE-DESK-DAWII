package com.rest.service.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_reclamo")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reclamo")
    private int idReclamo;

    @Column(name = "cod_usuario")
    private int codUsuario;

    @Column(name = "dni_usuario")
    private int dniUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_reclamo")
    private LocalDate fechaReclamo;  // Cambiado a LocalDate

    public Reclamo() {
    }

    // Getters y setters
    public int getIdReclamo() {
        return idReclamo;
    }
    

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }
    public LocalDate getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(LocalDate fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
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
}
