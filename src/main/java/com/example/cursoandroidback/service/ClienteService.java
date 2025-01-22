package com.example.cursoandroidback.service;

import com.example.cursoandroidback.entity.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listarClientes();
    Cliente obtenerClientePorId(Long id);
    Cliente guardarCliente(Cliente cliente);
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
}
