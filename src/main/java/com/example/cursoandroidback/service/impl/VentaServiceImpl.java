package com.example.cursoandroidback.service.impl;

import com.example.cursoandroidback.entity.Venta;
import com.example.cursoandroidback.repository.VentaRepository;
import com.example.cursoandroidback.service.VentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public Venta guardarVenta(Venta venta) {
        venta.getDetalles().forEach(detalle -> detalle.setVenta(venta));
        return ventaRepository.save(venta);
    }

    @Override
    @Transactional
    public Venta actualizarVenta(Long id, Venta venta) {
        return ventaRepository.findById(id).map(ventaExistente -> {
            validateVenta(venta);

            // Actualizar campos principales de la venta
            ventaExistente.setSerie(venta.getSerie());
            ventaExistente.setNumero(venta.getNumero());
            ventaExistente.setFechaEmision(venta.getFechaEmision());
            ventaExistente.setCliente(venta.getCliente());
            ventaExistente.setTipoComprobante(venta.getTipoComprobante());
            ventaExistente.setMoneda(venta.getMoneda());
            ventaExistente.setTotal(venta.getTotal());

            // Manejar detalles de venta
            // Limpiar los detalles existentes
            ventaExistente.getDetalles().clear();

            // Agregar los nuevos detalles
            venta.getDetalles().forEach(detalle -> {
                detalle.setVenta(ventaExistente); // Vincular cada detalle a la venta existente
                ventaExistente.getDetalles().add(detalle);
            });

            return ventaRepository.save(ventaExistente);
        }).orElseThrow(() -> new NoSuchElementException("Venta no encontrada con ID: " + id));
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta venta = obtenerVentaPorId(id);
        ventaRepository.deleteById(id);
    }
    private void validateVenta(Venta venta) {
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("La venta debe estar asociada a un cliente.");
        }

        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La venta debe tener al menos un detalle.");
        }

        venta.getDetalles().forEach(detalle -> {
            if (detalle.getProducto() == null) {
                throw new IllegalArgumentException("Cada detalle debe estar asociado a un producto.");
            }

            if (detalle.getCantidad() == null || detalle.getCantidad().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
            }
        });
    }
}
