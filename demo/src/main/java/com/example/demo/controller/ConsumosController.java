package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.modelo.Consumos;
import com.example.demo.modelo.Habitaciones;
import com.example.demo.modelo.Servicios;
import com.example.demo.modelo.Clientes;


import com.example.demo.repositorio.ClientesRepository;
import com.example.demo.repositorio.ConsumosRepository;
import com.example.demo.repositorio.HabitacionesRepository;
import com.example.demo.repositorio.ServiciosRepository;


@Controller
public class ConsumosController {

    @Autowired
    private ConsumosRepository consumosRepository;

    @Autowired
    private HabitacionesRepository habitacionesRepository;

    @Autowired
    private ClientesRepository clientesRepository;
    
    @Autowired
    private ServiciosRepository serviciosRepository;

    @Autowired
    private MongoTemplate mongoTemplate;   
    
    @GetMapping("/consumos")
    public String getConsumos(Model model){
        model.addAttribute("consumos", consumosRepository.findAll());
        return "consumos";
    }
    
    @GetMapping("/consumosForm")
        public String consumosForm(Model model){
        model.addAttribute("nuevoConsumo", new Consumos());
        return "crearConsumoForm";
    }
    
   @PostMapping("/crearConsumos")
    public String crearConsumo(@ModelAttribute("nuevoConsumo") Consumos nuevoConsumo) {


        // Buscar la habitación por su número
        Habitaciones habitacion = habitacionesRepository.findByNumero(nuevoConsumo.getHabitaciones().get(0).getNumero()).get(0);
        Clientes cliente = clientesRepository.findByDocumento(nuevoConsumo.getClientes().get(0).getDocumento());
        Servicios servicio = serviciosRepository.findByNombre(nuevoConsumo.getServicios().get(0).getNombre()).get(0);

        // Asociar la habitación a la reserva
        nuevoConsumo.setHabitaciones(Collections.singletonList(habitacion));
        nuevoConsumo.setClientes(Collections.singletonList(cliente));
        nuevoConsumo.setServicios(Collections.singletonList(servicio));

        // Guardar la reserva
        consumosRepository.save(nuevoConsumo);

        return "redirect:/consumos";
    }

    @PostMapping("/actualizarConsumo")
    public String actualizarConsumo(@ModelAttribute("consumo") Consumos consumo) {
        // save the tipo_habitacion object
        Habitaciones habitacion = consumo.getHabitaciones().get(0);
        Servicios servicio = consumo.getServicios().get(0);
        Clientes cliente = consumo.getClientes().get(0);
        if (cliente != null || habitacion != null || servicio != null){
            habitacionesRepository.save(habitacion);
            clientesRepository.save(cliente);
            serviciosRepository.save(servicio);
        }
        consumosRepository.save(consumo);
        return "redirect:/consumos";
    }

    @GetMapping("/editarConsumo")
    public String editarConsumo(@RequestParam("id") String id, Model model) {
        Consumos consumo = consumosRepository.findById(id).orElse(null);
    
        model.addAttribute("consumo", consumo);
    
        return "editarConsumoForm";
    }
    
    @PostMapping("/eliminarConsumos")
    public String eliminarReservas(@RequestParam(name = "id", required = false) String id){
        consumosRepository.deleteById(id);
        return "redirect:/consumos";
    }

    @GetMapping("/buscarPorNumero")
    public String buscarPorNumero(@RequestParam("numero") String numero, Model model) {
        // Busca las habitaciones por número
        List<Habitaciones> habitaciones = habitacionesRepository.findByNumero(numero);
    
        // Busca los consumos por la lista de habitaciones obtenida
        List<Consumos> consumos = consumosRepository.findByHabitaciones(habitaciones);
    
        // Haz lo que necesites con la lista de consumos, por ejemplo, agregarla al modelo para mostrar en la vista.
        model.addAttribute("consumos", consumos);
    
        // Devuelve el nombre de la vista que mostrará los resultados
        return "consumos";
    }

    }
