package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.modelo.Habitaciones;
public interface HabitacionesRepository extends MongoRepository<Habitaciones, String>{

    
}
