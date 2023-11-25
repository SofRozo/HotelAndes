package com.example.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "bebidas_tipos")
public class BebidaTipos {

    @Id
    private String id;

    // Nombre del tipo de bebida
    private String nombre;

    // Lista de bebidas embebidas en el documento principal
    private List<BebidaEmbedded> Bebidas;

    public BebidaTipos(){
        //Constructor vacio para el uso de Spring
    }

    public BebidaTipos(String nombre, List<BebidaEmbedded> bebidas) {
        
        //Constructor con atributos. Note que si no se define una id, mongo genera una automaticamente 
        this.nombre = nombre;
        this.Bebidas = bebidas;
    }
    
    //<---------- Getters and Setters ---------->
    public String getNombre(){
        return nombre;
    }

    public void setNombre(String name){
        this.nombre = name;
    }   

    public List<BebidaEmbedded> getBebidas(){
        return Bebidas;
    }

    public void setBebidas(List<BebidaEmbedded> bebidas){
        this.Bebidas = bebidas;
    }

    public void addBebida(BebidaEmbedded bebida){
        this.Bebidas.add(bebida);
    }




    
    
}
