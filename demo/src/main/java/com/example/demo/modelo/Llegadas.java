package com.example.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document (collection = "llegadas")
public class Llegadas {

    @Id
    private String id;

    @Field("fecha_llegada")
    private String fecha_llegada;

    @DBRef
    private List<Reservas> reserva;

    @DBRef
    private List<Clientes> cliente;


    public Llegadas(){}

    public Llegadas(String fecha_llegada){
        this.fecha_llegada = fecha_llegada;
    }

    public Llegadas(String fecha_llegada, List<Reservas> reserva, List<Clientes> cliente){
        this.fecha_llegada = fecha_llegada;
        this.reserva = reserva;
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(String fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public List<Reservas> getReserva() {
        return reserva;
    }

    public void setReserva(List<Reservas> reserva) {
        this.reserva = reserva;
    }

    public List<Clientes> getCliente() {
        return cliente;
    }

    public void setCliente(List<Clientes> cliente) {
        this.cliente = cliente;
    }

    



    
}
