package com.example.cursoandroidback.repository;

import com.example.cursoandroidback.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}