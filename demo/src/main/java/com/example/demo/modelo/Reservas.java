package com.example.demo.modelo;
import java.util.List;
import java.util.Date;

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
    private Date fecha_fin;

    @Field("estado")
    private Date estado;

    public Reservas(){}

    public Reservas(List<Habitaciones> habitaciones, List<Clientes> clientes, String fecha_inicio, Date fecha_fin, Date estado){
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

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Date getEstado() {
        return estado;
    }

    public void setEstado(Date estado) {
        this.estado = estado;
    }

    
}
