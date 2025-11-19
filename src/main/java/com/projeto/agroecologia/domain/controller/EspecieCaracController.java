package com.projeto.agroecologia.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.agroecologia.domain.model.EspecieCarac;
import com.projeto.agroecologia.domain.service.EspecieCaracService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/caracteristica")
@RequiredArgsConstructor
public class EspecieCaracController {

    private final EspecieCaracService service;

    @GetMapping
    public List<EspecieCarac> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/filtrar-por-especie/{id}")
    public List<EspecieCarac> listarPorEspecie(@PathVariable Long id) {
        return service.listarPorEspecie(id);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<EspecieCarac> associar(@RequestBody EspecieCarac especieCarac) {
        Optional<EspecieCarac> result = service.associar(
                especieCarac.getEspecie().getId(),
                especieCarac.getCarac().getId(),
                especieCarac.getValorCarac()
        );
        return result.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.badRequest().build());
    }
}
