package com.example.cursoandroidback.service.impl;

import com.example.cursoandroidback.entity.Producto;
import com.example.cursoandroidback.repository.ProductoRepository;
import com.example.cursoandroidback.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto) {
        Producto existente = obtenerProductoPorId(id);
        existente.setCodigo(producto.getCodigo());
        existente.setDescripcion(producto.getDescripcion());
        existente.setPrecioUnitario(producto.getPrecioUnitario());
        existente.setUnidadMedida(producto.getUnidadMedida());
        return productoRepository.save(existente);
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
