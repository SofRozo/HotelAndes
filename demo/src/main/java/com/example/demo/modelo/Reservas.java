package com.example.demo.modelo;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document (collection = "reservas")
public class Reservas {

    @Id
    private String id;

    @DBRef
    private List<Habitaciones> habitaciones;

    @DBRef
    private List<Clientes> clientes;

    @Field("fecha_inicio")
    private String fecha_inicio;

    @Field("fecha_fin")
    private String fecha_fin;

    @Field("estado")
    private String estado;

    public Reservas(){}

    public Reservas(String fecha_inicio, String fecha_fin, String estado){
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
    }

    public Reservas(List<Habitaciones> habitaciones, List<Clientes> clientes, String fecha_inicio, String fecha_fin, String estado){
        this.habitaciones = habitaciones;
        this.clientes = clientes;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Habitaciones> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitaciones> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public List<Clientes> getClientes() {
        return clientes;
    }

    public void setClientes(List<Clientes> clientes) {
        this.clientes = clientes;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
