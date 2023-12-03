package com.example.demo.controller;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repositorio.ReservasRepository;
import com.example.demo.modelo.Clientes;
import com.example.demo.modelo.Habitaciones;
import com.example.demo.modelo.Reservas;
import com.example.demo.repositorio.ClientesRepository;
import com.example.demo.repositorio.HabitacionesRepository;

@Controller
public class ReservasController {

    @Autowired
    private ReservasRepository reservasRepository;

    @Autowired
    private HabitacionesRepository habitacionesRepo;

    @Autowired
    private ClientesRepository clientesRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/reservas")
    public String getReservas(Model model){
        model.addAttribute("reservas", reservasRepository.findAll());
        return "reservas";
    }

    @PostMapping("/eliminarReservas")
    public String eliminarReservas(@RequestParam(name = "id", required = false) String id){
        reservasRepository.deleteById(id);
        return "redirect:/reservas";
    }

    @GetMapping("/reservasForm")
    public String reservasForm(Model model){
        model.addAttribute("reservas", new Reservas());
        model.addAttribute("habitaciones", habitacionesRepo.findAll());
        model.addAttribute("clientes", new Clientes());
        return "reservasForm";
    }

    @PostMapping("/crearReservas")
    public String crearReserva(@ModelAttribute("nuevaReserva") Reservas nuevaReserva, @RequestParam("habitacionId") String habitacionId){
        // Buscar la habitación existente en la base de datos

        Clientes cliente = new Clientes(
            nuevaReserva.getClientes().get(0).getNombre(),
            nuevaReserva.getClientes().get(0).getApellido(),
            nuevaReserva.getClientes().get(0).getDocumento(),
            nuevaReserva.getClientes().get(0).getTelefono(),
            nuevaReserva.getClientes().get(0).getEmail(),
            nuevaReserva.getClientes().get(0).getTipo_documento()
        );
        
        // Guardar el cliente en la base de datos
        clientesRepo.save(cliente);

        nuevaReserva.setClientes(Collections.singletonList(cliente));

        Habitaciones habitacionExistente = habitacionesRepo.findById(habitacionId).orElse(null);

        if (habitacionExistente != null) {
            // Si la habitación existe, añadirla a la reserva
            nuevaReserva.setHabitaciones(Collections.singletonList(habitacionExistente));
            // Guardar la reserva en la base de datos
            reservasRepository.save(nuevaReserva);
        } else {
            // Si la habitación no existe, redirigir a una página de error o manejar el error de otra manera
            return "redirect:/error";
        }

        return "redirect:/reservas";
    }

    @GetMapping("/mostrarResultadosAgregacionHabitacion")
    public String mostrarResultadosHabitacion(Model model){
        LookupOperation lookupOperation1 = LookupOperation.newLookup()
                .from("habitaciones")
                .localField("habitaciones")
                .foreignField("_id")
                .as("Lista_habitaciones");
        LookupOperation lookupOperation2 = LookupOperation.newLookup()
                .from("tipos_habitacion")
                .localField("Lista_habitaciones.tipo_habitacion")
                .foreignField("_id")
                .as("Lista_tipos_habitacion");
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation1, lookupOperation2);
        
        List<Reservas> reservas = mongoTemplate.aggregate(aggregation, "reservas", Reservas.class).getMappedResults();
        model.addAttribute("reservas", reservas);
        return "resultadosHabitacion"; 
    }

    @GetMapping("/mostrarResultadosAgregacionClientes")
    public String mostrarResultadosClientes(Model model){

        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("clientes")
                .localField("clientes")
                .foreignField("_id")
                .as("Lista_clientes");
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);
        
        List<Reservas> reservas = mongoTemplate.aggregate(aggregation, "reservas", Reservas.class).getMappedResults();
        model.addAttribute("reservas", reservas);
        return "resultadosClientes"; 
    }


}
