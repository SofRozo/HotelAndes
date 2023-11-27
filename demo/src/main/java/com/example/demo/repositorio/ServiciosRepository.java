package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.modelo.Servicios;

public interface ServiciosRepository extends MongoRepository<Servicios, String> {
    
    List<Servicios> findByNombre(String nombre);
}
