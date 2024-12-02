package com.rest.service.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_importancia")
public class Importancia {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_importancia")
	private int idImportancia;
	
	@Column(name = "nombre_importancia")
	private String nombreImportancia;
	
	@OneToMany(mappedBy = "importancia", cascade = CascadeType.ALL)
	private List<Reclamo> reclamos;
	public Importancia() {
	}
	public int getIdImportancia() {
		return idImportancia;
	}
	public void setIdImportancia(int idImportancia) {
		this.idImportancia = idImportancia;
	}
	public String getNombreImportancia() {
		return nombreImportancia;
	}
	public void setNombreImportancia(String nombreImportancia) {
		this.nombreImportancia = nombreImportancia;
	}
	public List<Reclamo> getReclamos() {
		return reclamos;
	}
	public void setReclamos(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}

}
