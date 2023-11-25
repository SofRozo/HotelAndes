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

import com.example.demo.modelo.BebidaEmbedded;
import com.example.demo.modelo.BebidaTipos;
import com.example.demo.repositorio.BebidaTiposRepository;

@Controller
public class BebidaTiposController {

    @Autowired
    private BebidaTiposRepository btRepository;


    @GetMapping("/bt")
    public String obtenerTodasLasBebidasTipos(Model model){
        model.addAttribute("bts", btRepository.findAll());
        return "bebidaTipos";
    }

    @GetMapping("/")
    public String home(Model model){
        return "index";
    }

    @GetMapping("/btForm")
    public String mostrarFormulario(Model model) {
        // Creamos una instancia vacía para el nuevo BebidaTipos
        model.addAttribute("nuevoBebidaTipo", new BebidaTipos());
        return "bebidaTiposForm";
    }

    @PostMapping("/crearBebidaTipo")
    public String crearBebidaTipos(@ModelAttribute("nuevoBebidaTipo") BebidaTipos nuevoBebidaTipo) {

        // Creamos una nueva bebida utilizando los datos del formulario
        BebidaEmbedded nuevaBebida = new BebidaEmbedded(
            nuevoBebidaTipo.getBebidas().get(0).getNombre(),
            nuevoBebidaTipo.getBebidas().get(0).getGradoAlcohol()
        );

        // Agregamos la bebida a la lista de bebidas en el nuevo tipo de bebida
        nuevoBebidaTipo.setBebidas(Collections.singletonList(nuevaBebida));


        // Guardamos el nuevo tipo de bebida
        btRepository.save(nuevoBebidaTipo);
        return "redirect:/bt";
    }

    @GetMapping("/addBebida")
    public String añadirBebida(@RequestParam(name = "nombre", required = false) String nombre, Model model){
        model.addAttribute("nombreTipoBebida", nombre);
        model.addAttribute("bebida", new BebidaEmbedded());


        return "addBebidaForm";
    }

    @PostMapping("/deleteBebidaTipo")
    public String eliminarBebidaTipo(@RequestParam(name = "nombre", required = false) String nombre){
        
        for (BebidaTipos beb: btRepository.findByNombre(nombre))
        {
            btRepository.delete(beb);
        }

        return "redirect:/bt";
        
    }

    @PostMapping("/addBebidaSave")
    public String añadirBebidaSave(@RequestParam("nombreTipoBebida") String nombreTipoBebida,
    @ModelAttribute("bebida") BebidaEmbedded beb){

        // Creamos una nueva bebida utilizando los datos del formulario
        BebidaEmbedded nuevaBebida = new BebidaEmbedded(
            beb.getNombre(),
            beb.getGradoAlcohol()
        );

        System.out.println(nombreTipoBebida);
        //Buscamos los tipos de bebida con ese nombre
        List<BebidaTipos> bebs = btRepository.findByNombre(nombreTipoBebida);

        //Añadimos esa bebida a todos los tipos de bebidas con ese nombre
        for (BebidaTipos tipoBebida:bebs){
            if (tipoBebida.getBebidas() == null){
                List<BebidaEmbedded> emptyList = new ArrayList<>();
                tipoBebida.setBebidas(emptyList);
            }
            tipoBebida.addBebida(nuevaBebida);

            //Persistemos la modificacion en la base de datos
            btRepository.save(tipoBebida);
        }
        
        return "redirect:/bt";

    }

    
}
