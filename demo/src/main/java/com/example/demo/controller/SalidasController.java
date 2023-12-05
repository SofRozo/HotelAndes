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
import com.example.demo.modelo.Salidas;
import com.example.demo.repositorio.ClientesRepository;
import com.example.demo.repositorio.LlegadasRepository;
import com.example.demo.repositorio.ReservasRepository;
import com.example.demo.repositorio.SalidasRepository;

@Controller
public class SalidasController {

    @Autowired
    private SalidasRepository salidasRepository;

    @Autowired
    private ReservasRepository reservasRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/salidas")
    public String getSalidas(Model model){
        model.addAttribute("salidas", salidasRepository.findAll());
        return "salidas";
    }

    @GetMapping("/salidaCliente")
    public String buscarPorDocumento(@RequestParam("documento") String documento, Model model){
        Clientes cliente = clientesRepository.findByDocumento(documento);
        model.addAttribute("salidas", salidasRepository.findByCliente(cliente));
        return "salidas";
    }

    @PostMapping("/eliminarSalidas")
    public String eliminarSalidas(@RequestParam(name= "id", required = false) String id){
         salidasRepository.deleteById(id);
         return "redirect:/salidas";
     }

     
    @GetMapping("/salidasForm")
    public String salidasForm(Model model){
       model.addAttribute("nuevaSalida", new Salidas());
         return "crearSalidaForm";
     }

    @PostMapping("/crearSalidas")
    public String crearSalidas(@ModelAttribute("nuevaSalida") Salidas salida, Model model){
        Clientes cliente = clientesRepository.findByDocumento(salida.getCliente().get(0).getDocumento());
        Reservas reserva = reservasRepository.findById(salida.getReserva().get(0).getId()).get();
        salida.setReserva(Collections.singletonList(reserva));
        salida.setCliente(Collections.singletonList(cliente));
        salidasRepository.save(salida);
        return "redirect:/salidas";
    }

}
