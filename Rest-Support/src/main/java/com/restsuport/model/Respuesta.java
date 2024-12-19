package com.restsuport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_respuesta")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRespuesta;

    private String descripcionRespuesta;
    private LocalDate fechaRespuesta;

    // Campos relacionados con la operación, que provienen del OperacionDTO
    private int idOperacion;
    private String descripcionOperacion;

    // Constructor para crear respuesta
    public Respuesta(String descripcionRespuesta, LocalDate fechaRespuesta, int idOperacion, String descripcionOperacion) {
        this.descripcionRespuesta = descripcionRespuesta;
        this.fechaRespuesta = fechaRespuesta;
        this.idOperacion = idOperacion;
        this.descripcionOperacion = descripcionOperacion;
    }
}
