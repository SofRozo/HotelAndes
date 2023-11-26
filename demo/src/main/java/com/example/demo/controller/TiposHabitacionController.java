package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.modelo.TiposHabitacion;
import com.example.demo.repositorio.TiposHabitacionRepository;
import java.util.Arrays;

@Controller
public class TiposHabitacionController {

    @Autowired
    private TiposHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/crearTipoHabitacion")
    public String crearTipoHabitacion(Model model) {
        model.addAttribute("tipoHabitacionNueva", new TiposHabitacion());
        return "tipoHabitacionForm";
    }
    @PostMapping("/crearTipoHabitacionNueva")
    public String crearTipoHabitacionNueva(@ModelAttribute("tipoHabitacionNueva") TiposHabitacion tipoHabitacion) {
        // No es necesario dividir la cadena de dotación si se envía desde el formulario como una cadena separada por comas

        // Crear un nuevo objeto TipoHabitacion y guardar en el repositorio
        TiposHabitacion nueva = new TiposHabitacion(
            tipoHabitacion.getNombre(), 
            tipoHabitacion.getDescripcion(), 
            tipoHabitacion.getDotacion(), // Usar directamente la lista de dotación del formulario
            tipoHabitacion.getCapacidad(), 
            tipoHabitacion.getCosto()
        );

        tipoHabitacionRepository.save(nueva);
        return "redirect:/tiposHabitacion";
    }

    @PostMapping("/deleteTipoHabitacion")
    public String eliminarTipoHabitacion(@RequestParam(name = "id", required = false) String id){
        
        tipoHabitacionRepository.deleteById(id);

        return "redirect:/tiposHabitacion";
        
    }

    @GetMapping("/tiposHabitacion")
    public String obtenerTodosLosTiposHabitacion(Model model) {
        model.addAttribute("tiposHabitacion", tipoHabitacionRepository.findAll());
        return "tiposHabitacion";
    }

    @GetMapping("/buscarPorNombre")
    public String buscarPorNombre(@RequestParam("nombre") String nombre, Model model) {
        List<TiposHabitacion> tiposHabitacion = tipoHabitacionRepository.findByNombre(nombre);
        model.addAttribute("tiposHabitacion", tiposHabitacion);
        return "tiposHabitacion";
    }

    
}
