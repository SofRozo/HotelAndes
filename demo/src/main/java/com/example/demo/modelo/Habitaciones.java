package com.example.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document (collection = "habitaciones")
public class Habitaciones {

    @Id
    private String id;

    @Field("numero")
    private String numero;

    @DBRef
    private List<TiposHabitacion> tipo_habitacion;

    @Field("estado")
    private String estado;

    @Field("detalles")
    private String detalles;

    @DBRef
    private List<Reservas> reservas;

    // Constructor vacio para el uso de Spring
    public Habitaciones(){}

    // Constructor con solo el numero de la habitacion
    public Habitaciones(String numero){
        this.numero = numero;
    }

    public Habitaciones(String numero, List<TiposHabitacion> tipo_habitacion, String estado, String detalles, List<Reservas> reservas){
        this.numero = numero;
        this.tipo_habitacion = tipo_habitacion;
        this.estado = estado;
        this.detalles = detalles;
        this.reservas = reservas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<TiposHabitacion> getTipo_habitacion() {
        return tipo_habitacion;
    }

    public void setTipo_habitacion(List<TiposHabitacion> tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public List<Reservas> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservas> reservas) {
        this.reservas = reservas;
    }

    



                  



    
}
