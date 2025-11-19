package com.projeto.agroecologia.domain.controller;

import com.projeto.agroecologia.domain.model.Carac;
import com.projeto.agroecologia.domain.service.CaracService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/caracs")
public class CaracController {

    @Autowired
    private CaracService caracService;

    @GetMapping
    public List<Carac> findAll() {
        return caracService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Carac> findById(@PathVariable Long id) {
        return caracService.findById(id);
    }

    @PostMapping("/criar")
    public Carac create(@RequestBody Carac carac) {
        return caracService.save(carac);
    }

    @PutMapping("/atualizar/{id}")
    public Carac update(@PathVariable Long id, @RequestBody Carac carac) {
        carac.setId(id);
        return caracService.save(carac);
    }

    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable Long id) {
        caracService.deleteById(id);
    }
}
