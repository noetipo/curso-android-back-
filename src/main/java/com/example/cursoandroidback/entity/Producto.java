package com.example.cursoandroidback.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo; // Código del producto exigido por SUNAT
    private String descripcion;
    private BigDecimal precioUnitario;
    private String unidadMedida; // Ejemplo: "NIU" para unidades

    @Column(nullable = false)
    private Boolean estado = true; // Activo o inactivo
}
