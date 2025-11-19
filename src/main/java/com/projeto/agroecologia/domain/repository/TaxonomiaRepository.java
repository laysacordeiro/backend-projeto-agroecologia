package com.projeto.agroecologia.domain.repository;

import com.projeto.agroecologia.domain.model.Taxonomia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaxonomiaRepository extends JpaRepository<Taxonomia, Long> {
    Optional<Taxonomia> findByNomeIgnoreCaseAndNivelIgnoreCase(String nome, String nivel);
}