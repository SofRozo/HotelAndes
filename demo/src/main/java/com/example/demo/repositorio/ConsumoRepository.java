package com.example.demo.repositorio;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.modelo.Consumo;

public interface ConsumoRepository extends MongoRepository<Consumo, String>{

    List<Consumo> findByClienteId(String clienteId);
    
}
