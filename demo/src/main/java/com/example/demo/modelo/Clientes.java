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


    public Clientes(String nombre, String apellido, String email, String telefono, String documento, String tipo_documento, List<Reservas> reservas){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.documento = documento;
        this.tipo_documento = tipo_documento;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public List<Reservas> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reservas> reservas) {
        this.reservas = reservas;
    }

                                                                                                                                                                                                                                                                            

    
}
