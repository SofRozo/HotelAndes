package com.example.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document (collection = "clientes")
public class Clientes {

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("apellido")
    private String apellido;

    @Field("email")
    private String email;

    @Field("telefono")
    private String telefono;

    @Field("direccion")
    private String direccion;


    @Field("documento")
    private String documento;

    @Field("tipo_documento")
    private String tipo_documento;

    @DBRef
    private List<Reservas> reservas;

    // Constructor vacio para el uso de Spring
    public Clientes(){}

    // Constructor con solo el nombre del cliente
    public Clientes(String nombre){
        this.nombre = nombre;
    }

    // Constructor con el nombre del cliente y una lista de reservas
    public Clientes(String nombre, List<Reservas> reservas){
        this.nombre = nombre;
        this.reservas = reservas;
    }

    //setter para agregar una referencia de reserva a las reservas que se tienen
    public void addReserva(Reservas res){
        reservas.add(res);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

                                                                                                                                                                                                                                                                            

    
}
