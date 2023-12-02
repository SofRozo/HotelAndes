package com.example.demo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.modelo.Clientes;

public interface ClientesRepository extends MongoRepository<Clientes, String> {
    
}
