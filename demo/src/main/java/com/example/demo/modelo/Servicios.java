package com.example.demo.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "servicios")
public class Servicios {

    @Id
    private String id;
    
    private String nombre;
    private String descripcion;
    private String tipo_servicio;

    private List<ProductosMenuEmbedded> productos_menu;

    public Servicios(){
        //Constructor vacio para el uso de Spring
    }

    public Servicios(String nombre, String descripcion, String tipo_servicio, List<ProductosMenuEmbedded> productos_menu) {
        
        //Constructor con atributos. Note que si no se define una id, mongo genera una automaticamente 
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo_servicio = tipo_servicio;
        this.productos_menu = productos_menu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    

    //<---------- Getters and Setters ---------->
    public String getNombre(){
        return nombre;
    }

    public List<ProductosMenuEmbedded> getProductos_menu() {
        return productos_menu;
    }

    public void setProductos_menu(List<ProductosMenuEmbedded> productos_menu) {
        this.productos_menu = productos_menu;
    }

    public void addProductoMenu(ProductosMenuEmbedded producto_menu){
        this.productos_menu.add(producto_menu);
    }
    
}
