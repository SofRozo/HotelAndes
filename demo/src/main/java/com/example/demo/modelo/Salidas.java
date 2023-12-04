package com.example.demo.modelo;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document (collection = "salidas")
public class Salidas {

    @Id
    private String id;

    @Field("fecha_salida")
    private String fecha_salida;

    @DBRef
    private List<Reservas> reserva;

    @DBRef
    private List<Clientes> cliente;

    public Salidas(){}

    public Salidas(String fecha_salida){
        this.fecha_salida = fecha_salida;
    }

    public Salidas(String fecha_salida, List<Reservas> reserva, List<Clientes> cliente){
        this.fecha_salida = fecha_salida;
        this.reserva = reserva;
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
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
