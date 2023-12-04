package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.modelo.ProductosMenuEmbedded;
import com.example.demo.modelo.Servicios;
import com.example.demo.repositorio.ServiciosRepository;

@Controller
public class ServiciosController {
    
        @Autowired
        private ServiciosRepository serviciosRepository;

        @GetMapping("/servicios")
        public String obtenerTodosLosServicios(Model model){
            model.addAttribute("servicios", serviciosRepository.findAll());
            return "servicios";
        }

        @GetMapping("/buscarPorNombreServicio")
        public String buscarPorNombreServicio(@RequestParam("nombre") String nombre, Model model) {
            List<Servicios> servicios = serviciosRepository.findByNombre(nombre);
            model.addAttribute("servicios", servicios);
            return "servicios";
        }



        @GetMapping("/serviciosForm")
        public String mostrarFormulario(Model model) {
            // Creamos una instancia vacía para el nuevo Servicios
            model.addAttribute("nuevoServicio", new Servicios());
            return "serviciosForm";
        }

        @PostMapping("/crearServicio")
        public String crearServicio(@ModelAttribute("nuevoServicio") Servicios nuevoServicio) {

            // Creamos una nueva bebida utilizando los datos del formulario
            ProductosMenuEmbedded nuevoProducto = new ProductosMenuEmbedded(
                nuevoServicio.getProductos_menu().get(0).getNombre(),
                nuevoServicio.getProductos_menu().get(0).getDescripcion(),
                nuevoServicio.getProductos_menu().get(0).getPrecio()
            );

            // Agregamos la bebida a la lista de bebidas en el nuevo tipo de bebida
            nuevoServicio.setProductos_menu(Collections.singletonList(nuevoProducto));
            serviciosRepository.save(nuevoServicio);
            return "redirect:/servicios";
    
        }

        @GetMapping("/addProductosMenu")
        public String addProductosMenu(@RequestParam(name = "nombre", required = false) String nombre, Model model) {
            model.addAttribute("nombreServicio", nombre); // Agregar el valor de nombreServicio al modelo
            model.addAttribute("nuevoProducto", new ProductosMenuEmbedded());
            return "addProductosMenuForm";
        }
        

        
        @PostMapping("/deleteServicio")
        public String eliminarServicio(@RequestParam(name = "nombre", required = false) String nombre){
            
            for (Servicios ser: serviciosRepository.findByNombre(nombre))
            {
                serviciosRepository.delete(ser);
            }

            return "redirect:/servicios";
            
        }

        @PostMapping("/addProductoMenuSave")
        public String añadirProductoMenuSave(@RequestParam("nombreServicio") String nombreServicio,
            @ModelAttribute("nuevoProducto") ProductosMenuEmbedded beb){

                // Creamos una nueva bebida utilizando los datos del formulario
                ProductosMenuEmbedded nuevoPM = new ProductosMenuEmbedded(
                    beb.getNombre(),
                    beb.getDescripcion(),
                    beb.getPrecio()
                );

                System.out.println(nombreServicio);
                //Buscamos los tipos de bebida con ese nombre
                List<Servicios> servs = serviciosRepository.findByNombre(nombreServicio);

                //Añadimos esa bebida a todos los tipos de bebidas con ese nombre
                for (Servicios servicios:servs){
                    if (servicios.getProductos_menu() == null){
                        List<ProductosMenuEmbedded> emptyList = new ArrayList<>();
                        servicios.setProductos_menu(emptyList);
                    }
                    servicios.addProductoMenu(nuevoPM);

                    //Persistemos la modificacion en la base de datos
                    serviciosRepository.save(servicios);
                }
                
                return "redirect:/servicios";

            }






}