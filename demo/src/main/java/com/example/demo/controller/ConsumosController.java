package com.example.demo.controller;

import java.util.Collections;

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
import com.example.demo.modelo.Reservas;
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
        Habitaciones habitacion = habitacionesRepository.findByNumero(nuevoConsumo.getHabitaciones().get(0).getNumero());
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
    

// @PostMapping("/crearConsumoNuevo")
// public String crearConsumoNuevo(@ModelAttribute("consumoNuevo") Consumos consumo){
//     Consumos nuevo = new Consumos(
//         consumo.getConsumoes(),
//         consumo.getServicioNombre(),
//         consumo.getClientes(),
//         consumo.getDescripcion(),
//         consumo.getCosto(),
//         consumo.getFecha()
//     );

//     consumosRepository.save(nuevo);
//     return "redirect:/consumo";
// }

        // @PostMapping("/deleteConsumo")
        // public String eliminarConsumo(@RequestParam(name = "id", required = false) String id){
        //     consumoRepository.deleteById(id);
            
        //     return "redirect:/consumo";
        // }




        // @GetMapping("/consumoCliente")
        // public String consumoPorCliente(@RequestParam("cliente_id") String cliente_id, Model model){
        //     List<Consumo> consumos = consumoRepository.findByClienteId(cliente_id);
        //     model.addAttribute("consumo", consumos);
        //     return "consumo";
        // }
        }
