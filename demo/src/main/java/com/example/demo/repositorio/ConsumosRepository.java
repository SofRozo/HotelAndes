package com.example.demo.repositorio;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.modelo.Consumos;

public interface ConsumosRepository extends MongoRepository<Consumos, String>{
  
}
