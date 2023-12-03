package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.modelo.Consumos;
import com.example.demo.repositorio.ClientesRepository;
import com.example.demo.repositorio.ConsumosRepository;
import com.example.demo.repositorio.HabitacionesRepository;

@Controller
public class ConsumosController {

    @Autowired
    private ConsumosRepository consumosRepository;

    @Autowired
    private HabitacionesRepository habitacionesRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private MongoTemplate mongoTemplate;   
    
        @GetMapping("/consumos")
        public String getConsumos(Model model){
            model.addAttribute("consumos", consumosRepository.findAll());
            return "consumos";
        }
        
    // @GetMapping("/crearConsumo")
    // public String crearConsumo(Model model){
    //     model.addAttribute("consumoNuevo", new Consumos());
    //     return "consumoForm";
    // }

// @PostMapping("/crearConsumoNuevo")
// public String crearConsumoNuevo(@ModelAttribute("consumoNuevo") Consumos consumo){
//     Consumos nuevo = new Consumos(
//         consumo.getHabitaciones(),
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
