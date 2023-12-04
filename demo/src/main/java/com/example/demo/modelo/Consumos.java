package com.example.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "consumos")
public class Consumos {

    @Id
    private String id;

    @DBRef
    public List<Habitaciones> habitaciones;

    @DBRef
    private List<Servicios> servicios;

    @DBRef
    private List<Clientes> clientes;

    @Field("descripcion")
    private String descripcion;

    @Field("costo")
    private Double costo;

    @Field("fecha")
    private String fecha;

    public Consumos(){}

    public Consumos(String descripcion, Double costo, String fecha){
        this.descripcion = descripcion;
        this.costo = costo;
        this.fecha = fecha;
    }

    public Consumos(List<Habitaciones> habitaciones, List<Servicios> servicios,
    List<Clientes> clientes, 
    String descripcion, Double costo, String fecha)
    {

        this.habitaciones = habitaciones;
        this.servicios = servicios;
        this.clientes = clientes;
        this.descripcion = descripcion;
        this.costo = costo;
        this.fecha = fecha;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public List<Habitaciones> getHabitaciones(){
        return this.habitaciones;
    }

    public void setHabitaciones(List<Habitaciones> habitaciones){
        this.habitaciones = habitaciones;
    }

    public List<Servicios> getServicios(){
        return this.servicios;
    }

    public void setServicios(List<Servicios> servicios){
        this.servicios = servicios;
    }

    public List<Clientes> getClientes(){
        return this.clientes;
    }
    public void setClientes(List<Clientes> clientes){
        this.clientes = clientes;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public Double getCosto(){
        return this.costo;
    }
    
    public void setCosto(Double costo){
        this.costo = costo;
    }
    
    public String getFecha(){
        return this.fecha;
    }
    
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    
}   

