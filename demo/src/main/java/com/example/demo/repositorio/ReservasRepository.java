package com.example.demo.repositorio;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.modelo.Reservas;

public interface ReservasRepository extends MongoRepository<Reservas, String> {
    
}
