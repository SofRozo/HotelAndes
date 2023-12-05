package com.example.demo.repositorio;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.modelo.Clientes;
import com.example.demo.modelo.Llegadas;
import com.example.demo.modelo.Salidas;

public interface SalidasRepository  extends MongoRepository<Salidas, String>{

        List<Salidas> findByCliente(Clientes cliente);
    
}
