package com.example.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document (collection = "tipo_bebidas")
public class TipoBebida {

    @Id
    private String id;

    @Field("nombre")
    private String nombre; 

    //DBRef porque estamos haciendo referencia a la id de los documentos invocados unicamente
    @DBRef
    private List<Bebida> bebidas;

    // Constructor vacio para el uso de Spring
    public TipoBebida(){}

    // Constructor con solo el nombre del tipo de bebida
    public TipoBebida(String nombre){
        this.nombre = nombre;
    }

    // Constructor con el nombre del tipo de bebida y una lista de bebidas
    public TipoBebida(String nombre, List<Bebida> bebidas){
        this.nombre = nombre;
        this.bebidas = bebidas;
    }

    //setter para agregar una referencia de bebida a las bebidas que se tienen
    public void addBebida(Bebida beb){
        bebidas.add(beb);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBebidas(List<Bebida> bebidas){
        this.bebidas = bebidas;
    }

    public List<Bebida> getBebidas(){
        return this.bebidas;
    }


}
