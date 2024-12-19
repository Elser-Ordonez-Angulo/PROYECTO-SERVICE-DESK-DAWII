package com.restsuport.dto;

public class RespuestaRequest {

    private String descripcionRespuesta;
    private int idOperacion;
    private String tipoEspecialista; // Puede ser "developer" o "technicalsupport"
    private int idEspecialista; // ID del especialista que dará la respuesta (Developer o TechnicalSupport)

    // Getters y Setters
    public String getDescripcionRespuesta() {
        return descripcionRespuesta;
    }

    public void setDescripcionRespuesta(String descripcionRespuesta) {
        this.descripcionRespuesta = descripcionRespuesta;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getTipoEspecialista() {
        return tipoEspecialista;
    }

    public void setTipoEspecialista(String tipoEspecialista) {
        this.tipoEspecialista = tipoEspecialista;
    }

    public int getIdEspecialista() {
        return idEspecialista;
    }

    public void setIdEspecialista(int idEspecialista) {
        this.idEspecialista = idEspecialista;
    }
}
