package com.rest.operator.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_Operacion")
public class Operacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOperacion;

    private String descripcion;

    private LocalDate fechaRecepcion;

    // Relación con Operadores
    @ManyToOne
    @JoinColumn(name = "idOperador", referencedColumnName = "idOperador", nullable = false)
    private Operadores operador;

    // Relación con Especialidad
    @ManyToOne
    @JoinColumn(name = "idEspecialidad", referencedColumnName = "idEspecialidad", nullable = false)
    private Especialidad especialidad;
    private int codreclamo;
    // Getters y Setters
    
    
    
    public Operacion() {
		super();
	}
    

	public Operacion(int idOperacion, String descripcion, LocalDate fechaRecepcion, Operadores operador,
			Especialidad especialidad, int codreclamo) {
		super();
		this.idOperacion = idOperacion;
		this.descripcion = descripcion;
		this.fechaRecepcion = fechaRecepcion;
		this.operador = operador;
		this.especialidad = especialidad;
		this.codreclamo = codreclamo;
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

    public Operadores getOperador() {
        return operador;
    }

    public void setOperador(Operadores operador) {
        this.operador = operador;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

	public int getCodreclamo() {
		return codreclamo;
	}

	public void setCodreclamo(int codreclamo) {
		this.codreclamo = codreclamo;
	}
    
}
