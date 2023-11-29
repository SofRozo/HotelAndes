package com.example.demo.modelo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "consumos")
public class Consumo {

    @Id
    private String id;

    @DBRef
    private String habitacion_id;

    @DBRef
    private Servicios servicio_nombre;

    @DBRef
    private String cliente_id;

    @Field("descripcion")
    private String descripcion;

    @Field("costo")
    private String costo;

    @Field("fecha")
    private Date fecha;

    public Consumo(){}

    public Consumo(String habitacion_id, Servicios servicio_nombre,
    String cliente_id, 
    String descripcion, String costo, Date fecha)
    {

        this.habitacion_id = habitacion_id;
        this.servicio_nombre = servicio_nombre;
        this.cliente_id = cliente_id;
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

    public String getHabitacionId(){
        return this.habitacion_id;
    }

    public void setHabitacionId(String habitacion_id){
        this.habitacion_id = habitacion_id;
    }

    public Servicios getServicioNombre(){
        return this.servicio_nombre;
    }

    public void setServicioNombre(Servicios servicio_nombre){
        this.servicio_nombre = servicio_nombre;
    }

    public String getClienteId(){
        return this.cliente_id;
    }

    public String getDescripcion(){
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getCosto(){
        return this.costo;
    }
    
    public void setCosto(String costo){
        this.costo = costo;
    }
    
    public Date getFecha(){
        return this.fecha;
    }
    
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    
}   

