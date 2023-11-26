package com.example.demo.repositorio;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.modelo.TiposHabitacion;

public interface TiposHabitacionRepository extends MongoRepository<TiposHabitacion, String> {

    List<TiposHabitacion> findByNombre(String nombre);

    List<TiposHabitacion> findByDotacion(String dotacion);


    
}
