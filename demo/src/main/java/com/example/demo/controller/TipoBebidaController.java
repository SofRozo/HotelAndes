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

import com.example.demo.modelo.Bebida;
import com.example.demo.modelo.BebidaEmbedded;
import com.example.demo.modelo.TipoBebida;
import com.example.demo.repositorio.BebidaRepository;
import com.example.demo.repositorio.TipoBebidaRepository;

@Controller
public class TipoBebidaController {

    @Autowired
    private TipoBebidaRepository tipoBebidaRepository;

    @Autowired
    private BebidaRepository bebidasRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/tipoBebidas")
    public String getTipoBebidas(Model model){

        model.addAttribute("tipos", tipoBebidaRepository.findAll());
        return "tiposBebidas";

    }

    @GetMapping("/mostrarResultadosAgregacion")
    public String mostrarResultados(Model model) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("bebidas")
                .localField("bebidas")
                .foreignField("_id")
                .as("Lista_bebidas_tipo");

        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);

        List<TipoBebida> tiposBebida = mongoTemplate.aggregate(aggregation, "tipo_bebidas", TipoBebida.class).getMappedResults();
        model.addAttribute("tiposBebida", tiposBebida);

        return "resultados";
    }
    

    @GetMapping("/tbForm")
    public String mostrarFormulario(Model model) {
        // Creamos una instancia vacía para el nuevo BebidaTipos
        model.addAttribute("nuevoTipoBebida", new TipoBebida());
        model.addAttribute("bebidasDisponibles", bebidasRepo.findAll());
        return "tiposBebidasForm";
    }

    @PostMapping("/crearTipoBebida")
    public String crearBebidaTipos(@ModelAttribute("nuevoTipoBebida") TipoBebida nuevoBebidaTipo) {

        // Creamos una nueva bebida utilizando los datos del formulario
        Bebida nuevaBebida = new Bebida(
            nuevoBebidaTipo.getBebidas().get(0).getNombre(),
            nuevoBebidaTipo.getBebidas().get(0).getGradoAlcohol()
        );

        bebidasRepo.save(nuevaBebida);

        // Agregamos la bebida a la lista de bebidas en el nuevo tipo de bebida
        nuevoBebidaTipo.setBebidas(Collections.singletonList(nuevaBebida));


        // Guardamos el nuevo tipo de bebida
        tipoBebidaRepository.save(nuevoBebidaTipo);
        return "redirect:/tipoBebidas";
    }
    
    /**
    @PostMapping
    public ResponseEntity<TipoBebida> crearTipoBebida(@RequestBody TipoBebida tipoBebida) {
        TipoBebida nuevoTipoBebida = tipoBebidaRepository.save(tipoBebida);
        return new ResponseEntity<>(nuevoTipoBebida, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TipoBebida>> obtenerTodasLasTipoBebidas() {
        List<TipoBebida> tipoBebidas = tipoBebidaRepository.findAll();
        return new ResponseEntity<>(tipoBebidas, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoBebida> obtenerTipoBebidaPorId(@PathVariable String id) {
        Optional<TipoBebida> tipoBebida = tipoBebidaRepository.findById(id);
        if (tipoBebida.isPresent()) {
            return new ResponseEntity<>(tipoBebida.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoBebida> actualizarTipoBebida(@PathVariable String id, @RequestBody TipoBebida tipoBebida) {
        Optional<TipoBebida> tipoBebidaExistente = tipoBebidaRepository.findById(id);
        if (tipoBebidaExistente.isPresent()) {
            tipoBebida.setId(id); // Asegura que el ID sea el mismo que el proporcionado en la URL
            TipoBebida tipoBebidaActualizada = tipoBebidaRepository.save(tipoBebida);
            return new ResponseEntity<>(tipoBebidaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TipoBebida> borrarTipoBebida(@PathVariable String id) {
        Optional<TipoBebida> tipoBebida = tipoBebidaRepository.findById(id);
        if (tipoBebida.isPresent()) {
            tipoBebidaRepository.deleteById(id);
            return new ResponseEntity<>(tipoBebida.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    */
}
