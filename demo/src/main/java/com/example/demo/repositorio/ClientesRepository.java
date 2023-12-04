package com.example.demo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.modelo.Clientes;
import java.util.List;


public interface ClientesRepository extends MongoRepository<Clientes, String> {
    Clientes findByDocumento(String documento);
}
