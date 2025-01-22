package com.example.cursoandroidback.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serie; // Serie del comprobante
    private String numero; // Número del comprobante
    private LocalDateTime fechaEmision;

    @ManyToOne
    private Cliente cliente;

    private String tipoComprobante; // Ejemplo: "01" para factura
    private String moneda; // Ejemplo: "PEN" para soles
    private Double total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("venta") // Evita bucles infinitos en la serialización JSON
    private List<VentaDetalle> detalles; // Relación con los detalles de la venta
}