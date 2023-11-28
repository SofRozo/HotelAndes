package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.modelo.Consumo;
import com.example.demo.repositorio.ConsumoRepository;

@Controller
public class ConsumoController {

    @Autowired
    private ConsumoRepository consumoRepository;

    @GetMapping("/crearConsumo")
    public String crearConsumo(Model model){
        model.addAttribute("ConsumoNuevo", new Consumo());
        return "consumoForm";
    }

    @PostMapping("crearConsumoNuevo")
    public String crearConsumoNuevo(@ModelAttribute("consumoNuevo") Consumo consumo){
        Consumo nuevo = new Consumo(
            consumo.getHabitacionId(),
            consumo.getServicioNombre(),
            consumo.getClienteId(),
            consumo.getDescripcion(),
            consumo.getCosto(),
            consumo.getFecha()
        );

        consumoRepository.save(nuevo);
        return "redirect:/consumo";
    }

        @PostMapping("/deleteConsumo")
        public String eliminarConsumo(@RequestParam(name = "id", required = false) String id){
            consumoRepository.deleteById(id);
            
            return "redirect:/consumo";
        }

        @GetMapping("/consumo")
        public String obtenerTodosLosConsumos(Model model){
            model.addAttribute("consumo", consumoRepository.findAll());
            return "consumo";
        }

        @GetMapping("consumoCliente")
        public String consumoPorCliente(@RequestParam("cliente_id") String cliente_id, Model model){
            List<Consumo> consumos = consumoRepository.findByClienteId(cliente_id);
            model.addAttribute("consumo", consumos);
            return "consumo";
        }
}
