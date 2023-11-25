package com.example.demo.repositorio;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.modelo.Bebida;

public interface BebidaRepository extends MongoRepository<Bebida, String> {
    
    List<Bebida> findByNombre(String nombre);
}
