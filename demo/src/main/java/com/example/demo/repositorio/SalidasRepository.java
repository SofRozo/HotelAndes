package com.example.demo.repositorio;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.modelo.Salidas;

public interface SalidasRepository  extends MongoRepository<Salidas, String>{
    
}
