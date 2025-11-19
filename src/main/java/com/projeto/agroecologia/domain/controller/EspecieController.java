package com.projeto.agroecologia.domain.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.agroecologia.domain.model.Especie;
import com.projeto.agroecologia.domain.service.EspecieService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/especies")
public class EspecieController {

    private final EspecieService especieService;

    public EspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }

    @GetMapping
    public ResponseEntity<List<Especie>> listarTodas() {
        List<Especie> especies = especieService.listarTodas();
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especie> buscarPorId(@PathVariable Long id) {
        return especieService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Especie> criar(@RequestBody Especie especie) {
        Especie nova = especieService.salvar(especie);
        return ResponseEntity.ok(nova);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Especie> atualizar(@PathVariable Long id, @RequestBody Especie especie) {
        try {
            Especie atualizada = especieService.atualizar(id, especie);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        especieService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
