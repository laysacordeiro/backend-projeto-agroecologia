package com.projeto.agroecologia.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.agroecologia.domain.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Long> {
    Especie findByNome(String nome);
    List<Especie> findByNomeContaining(String nome);
}