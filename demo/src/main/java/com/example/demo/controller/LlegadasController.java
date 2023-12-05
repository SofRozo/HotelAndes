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

import com.example.demo.modelo.Clientes;
import com.example.demo.modelo.Llegadas;
import com.example.demo.modelo.Reservas;
import com.example.demo.repositorio.ClientesRepository;
import com.example.demo.repositorio.LlegadasRepository;
import com.example.demo.repositorio.ReservasRepository;

@Controller
public class LlegadasController {

    @Autowired
    private LlegadasRepository llegadasRepository;
    
    @Autowired 
    private ReservasRepository reservasRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/llegadas")
    public String getLlegadas(Model model){
        model.addAttribute("llegadas", llegadasRepository.findAll());
        return "llegadas";
    }

    // @PostMapping("/eliminarLlegadas")
    // public String eliminarLlegadas(@RequestParam(name= "id", required = false) String id){
    //     llegadasRepository.deleteById(id);
    //     return "redirect:/llegadas";
    // }

    // @GetMapping("/llegadasForm")
    // public String llegadasForm(Model model){
    //     model.addAttribute("nuevaLlegada", new Llegadas());
    //     return "crearLlegadaForm";
    // }

    // @PostMapping("/crearLlegadas")
    // public String crearLlegada(@ModelAttribute("nuevaLlegada") Llegadas nuevaLlegada){

    //     Reservas reserva = reservasRepository.findById(nuevaLlegada.getReserva().get(0).getId()).get();
    //     Clientes cliente = clientesRepository.findByDocumento(nuevaLlegada.getCliente().get(0).getDocumento());
        
    //     nuevaLlegada.setReserva(Collections.singletonList(reserva));
    //     nuevaLlegada.setCliente(Collections.singletonList(cliente));

    //     llegadasRepository.save(nuevaLlegada);

    //     return "redirect:/llegadas";
    // }

}
