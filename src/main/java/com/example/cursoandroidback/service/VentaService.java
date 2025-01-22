package com.example.cursoandroidback.service;

import com.example.cursoandroidback.entity.Venta;

import java.util.List;

public interface VentaService {
    List<Venta> listarVentas();
    Venta obtenerVentaPorId(Long id);
    Venta guardarVenta(Venta venta);
    Venta actualizarVenta(Long id, Venta venta);
    void eliminarVenta(Long id);
}
