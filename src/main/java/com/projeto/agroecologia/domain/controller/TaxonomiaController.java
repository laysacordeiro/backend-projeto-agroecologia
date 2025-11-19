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

import com.projeto.agroecologia.domain.model.Taxonomia;
import com.projeto.agroecologia.domain.service.TaxonomiaService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/taxonomias")
public class TaxonomiaController {

    private final TaxonomiaService taxonomiaService;

    public TaxonomiaController(TaxonomiaService taxonomiaService) {
        this.taxonomiaService = taxonomiaService;
    }

    @GetMapping
    public ResponseEntity<List<Taxonomia>> listarTodas() {
        return ResponseEntity.ok(taxonomiaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taxonomia> buscarPorId(@PathVariable Long id) {
        return taxonomiaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Taxonomia> criar(@RequestBody Taxonomia taxonomia) {
        return ResponseEntity.ok(taxonomiaService.salvar(taxonomia));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Taxonomia> atualizar(@PathVariable Long id, @RequestBody Taxonomia taxonomia) {
        try {
            Taxonomia atualizada = taxonomiaService.atualizar(id, taxonomia);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        taxonomiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
