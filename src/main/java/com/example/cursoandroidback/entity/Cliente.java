package com.example.cursoandroidback.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipoDocumento; // Ejemplo: "RUC", "DNI"
    private String numeroDocumento;
    private String direccion;
}
