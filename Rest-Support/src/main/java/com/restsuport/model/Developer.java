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

@Entity
@Table(name = "developer") // Aquí se puede especificar el nombre de la tabla si es diferente
@Data // Lombok genera automáticamente getters, setters, etc.
@NoArgsConstructor
@Setter
@Getter
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String especialidad; // Ejemplo: Backend, Frontend, DevOps, etc.
    private String respuesta; // Respuesta específica para la operación
    
    private int idOperacion; // Campo añadido para almacenar la operación asociada
}
