package com.projeto.agroecologia.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.agroecologia.domain.model.Carac;
import com.projeto.agroecologia.domain.model.Especie;
import com.projeto.agroecologia.domain.model.EspecieCarac;

public interface EspecieCaracRepository extends JpaRepository<EspecieCarac, Long> {
      boolean existsByEspecieAndCarac(Especie especie, Carac carac);
      List<EspecieCarac> findByEspecieId(Long especieId);
      List<EspecieCarac> findByCaracId(Long caracId);
}
