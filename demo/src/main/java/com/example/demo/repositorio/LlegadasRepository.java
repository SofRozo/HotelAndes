package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.modelo.Clientes;
import com.example.demo.modelo.Llegadas;

public interface LlegadasRepository extends MongoRepository<Llegadas, String> {

    List<Llegadas> findByCliente(Clientes cliente);
    
}
