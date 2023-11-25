package com.example.demo.controller;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.modelo.Bebida;
import com.example.demo.modelo.BebidaTipos;
import com.example.demo.repositorio.BebidaRepository;



@Controller
public class BebidasController {

    @Autowired
    private BebidaRepository bebidaRepository;

    @GetMapping("/crearBebida")
    public String crearBebida(Model model) {
        model.addAttribute("bebidaNueva", new Bebida());
        return "bebidaForm";
    }

    @PostMapping("/crearBebidaNueva")
    public String crearBebidaNueva(@ModelAttribute("bebidaNueva") Bebida bebida) {
        Bebida nueva = new Bebida(
            bebida.getNombre(), bebida.getGradoAlcohol()
        );
        bebidaRepository.save(nueva);
        return "redirect:/bebidas";
    }

    @PostMapping("/deleteBebida")
    public String eliminarBebidaTipo(@RequestParam(name = "id", required = false) String id){
        
        bebidaRepository.deleteById(id);

        return "redirect:/bebidas";
        
    }

    @GetMapping("/bebidas")
    public String obtenerTodasLasBebidas(Model model) {
        model.addAttribute("bebidas", bebidaRepository.findAll());
        return "bebidas";
    } 
    /** 
    @GetMapping("/{id}")
    public ResponseEntity<Bebida> obtenerBebidaPorId(@PathVariable String id) {
        Optional<Bebida> bebida = bebidaRepository.findById(id);
        if (bebida.isPresent()) {
            return new ResponseEntity<>(bebida.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bebida> actualizarBebida(@PathVariable String id, @RequestBody Bebida bebida) {
        Optional<Bebida> bebidaExistente = bebidaRepository.findById(id);
        if (bebidaExistente.isPresent()) {
            //bebida.setId(id); // Asegura que el ID sea el mismo que el proporcionado en la URL
            Bebida bebidaActualizada = bebidaRepository.save(bebida);
            return new ResponseEntity<>(bebidaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBebida(@PathVariable String id) {
        Optional<Bebida> bebidaExistente = bebidaRepository.findById(id);
        if (bebidaExistente.isPresent()) {
            bebidaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    */

}


