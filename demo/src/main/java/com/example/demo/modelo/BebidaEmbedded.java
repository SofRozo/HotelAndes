package com.example.demo.modelo;


public class BebidaEmbedded {

    private String nombre;
    private double gradoAlcohol;

    public BebidaEmbedded(){}

    public BebidaEmbedded(String nombre, double grado){

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
}
