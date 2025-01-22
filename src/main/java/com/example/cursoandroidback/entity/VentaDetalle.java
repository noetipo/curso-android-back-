package com.example.cursoandroidback.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class VentaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Carga diferida para optimizar
    @JoinColumn(name = "venta_id", nullable = false) // Llave foránea en la tabla VentaDetalle
    @JsonIgnoreProperties("detalles") // Evita bucles infinitos en la serialización JSON
    private Venta venta; // Referencia a la entidad Venta

    @ManyToOne
    private Producto producto;

    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private BigDecimal igv; // Impuesto General a las Ventas
    private BigDecimal total;
}