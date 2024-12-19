package com.restsuport.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RespuestaDTO {
    private String descripcionRespuesta;
    private String nombreEspecialidad;
    private String descripcionOperacion;
    private LocalDate fechaRespuesta;
}
