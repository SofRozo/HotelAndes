package com.example.demo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "bebidas")
public class Bebida {
    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("gradoAlcohol")
    private double gradoAlcohol;

    public Bebida(){}

    public Bebida(String nombre, double grado){

        this.nombre = nombre;
        this.gradoAlcohol = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getGradoAlcohol() {
        return gradoAlcohol;
    }

    public void setGradoAlcohol(double gradoAlcohol) {
        this.gradoAlcohol = gradoAlcohol;
    }

    public String getId(){
        return id;
    }
}
