package com.example.demo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.modelo.TipoBebida;

public interface TipoBebidaRepository extends MongoRepository<TipoBebida, String> {
   
}
