package com.rest.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_usuario")

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Cod_Usuario")
	private int codUsuario;

	@Column(name = "Dni_Usuario")
	private int dniUsuario;

	@Column(name = "Nombre_Usuario")
	private String nombreUsuario;

	@Column(name = "Apellido_Usuario")
	private String apellidoUsuario;

	@Column(name = "Email_Usuario")
	private String emailUsuario;

	@Column(name = "Direccion_Usuario")
	private String direccionUsuario;

	@Column(name = "Clave")
	private String clave;
	
	public Usuario() {
		super();
	}

	public Usuario(int codUsuario, int dniUsuario, String nombreUsuario, String apellidoUsuario, String emailUsuario,
			String direccionUsuario, String clave) {
		super();
		this.codUsuario = codUsuario;
		this.dniUsuario = dniUsuario;
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.emailUsuario = emailUsuario;
		this.direccionUsuario = direccionUsuario;
		this.clave = clave;
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

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getDireccionUsuario() {
		return direccionUsuario;
	}

	public void setDireccionUsuario(String direccionUsuario) {
		this.direccionUsuario = direccionUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	

}
