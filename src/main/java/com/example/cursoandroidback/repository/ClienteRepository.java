package com.example.cursoandroidback.repository;

import com.example.cursoandroidback.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
