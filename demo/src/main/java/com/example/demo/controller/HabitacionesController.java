package com.example.demo.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.modelo.Habitaciones;
import com.example.demo.modelo.TiposHabitacion;
import com.example.demo.repositorio.HabitacionesRepository;
import com.example.demo.repositorio.TiposHabitacionRepository;

@Controller
public class HabitacionesController {

    @Autowired
    private HabitacionesRepository habitacionesRepository;

    @Autowired
    private TiposHabitacionRepository tipoHabitacionesRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/habitaciones")
    public String getHabitaciones(Model model){
        model.addAttribute("habitaciones", habitacionesRepository.findAll());
        return "habitaciones";
    }

    @GetMapping("/buscarPorNumeroHabitacion")
    public String buscarPorNumeroHabitacion(@RequestParam("numero") String numero, Model model) {
        Habitaciones habitacion = habitacionesRepository.findByNumero(numero);
        model.addAttribute("habitaciones", habitacion);
        return "habitaciones";
    }

    @GetMapping("/mostrarResultadosAgregacionTiposHabitacion")
    public String mostrarResultadosTiposHabitacion(Model model){
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("tipos_habitacion")
                .localField("tipos_habitacion")
                .foreignField("_id")
                .as("Lista_tipos_habitacion");
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);
        
       List<Habitaciones> habitaciones = mongoTemplate.aggregate(aggregation, "habitaciones", Habitaciones.class).getMappedResults();
        model.addAttribute("habitaciones", habitaciones);
        return "resultadosTiposHabitacion"; 
    }

    @PostMapping("/deleteHabitacion")
    public String eliminarTipoHabitacion(@RequestParam(name = "id", required = false) String id){
        
        habitacionesRepository.deleteById(id);

        return "redirect:/habitaciones";
        
    }

    @PostMapping("/actualizarHabitacion")
    public String actualizarHabitacion(@ModelAttribute("habitacion") Habitaciones habitacion) {
        // save the tipo_habitacion object
        TiposHabitacion tipo_habitacion = habitacion.getTipo_habitacion().get(0);
        if (tipo_habitacion != null) {
            tipoHabitacionesRepo.save(tipo_habitacion);
        }
        habitacionesRepository.save(habitacion);
        return "redirect:/habitaciones";
    }


    @GetMapping("/editarHabitacion")
    public String editarHabitacion(@RequestParam("id") String id, Model model) {
        Habitaciones habitacion = habitacionesRepository.findById(id).orElse(null);
        model.addAttribute("habitacion", habitacion);
        return "habitacionEditarForm";
    }


    @GetMapping("/habitacionForm")
    public String mostrarFormulario(Model model){
        // Creamos una instancia vacía para la nueva habitacion
        model.addAttribute("nuevaHabitacion", new Habitaciones());
        model.addAttribute("tiposHabitacionDisponibles", tipoHabitacionesRepo.findAll());
        return "habitacionesForm";
    }

    @PostMapping("/crearHabitacion")
    public String crearHabitacion(@ModelAttribute("nuevaHabitacion") Habitaciones nuevaHabitacion){
        // Crear un nuevo tipo de habitacion utilizando los datos del formulario
        TiposHabitacion nuevoTipoHabitacion = new TiposHabitacion(
            nuevaHabitacion.getTipo_habitacion().get(0).getNombre(),
            nuevaHabitacion.getTipo_habitacion().get(0).getDescripcion(),
            nuevaHabitacion.getTipo_habitacion().get(0).getDotacion(),
            nuevaHabitacion.getTipo_habitacion().get(0).getCapacidad(),
            nuevaHabitacion.getTipo_habitacion().get(0).getCosto()
        );

        // Guardar el nuevo tipo de habitacion en la base de datos
        tipoHabitacionesRepo.save(nuevoTipoHabitacion);

        // Establecer el tipo de habitacion en la nueva habitacion
        nuevaHabitacion.setTipo_habitacion(Collections.singletonList(nuevoTipoHabitacion));

        // Guardar la nueva habitacion
        habitacionesRepository.save(nuevaHabitacion);
        return "redirect:/habitaciones";
    }
    
}
