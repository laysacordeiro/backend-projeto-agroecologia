package com.projeto.agroecologia.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projeto.agroecologia.domain.model.Carac;
import com.projeto.agroecologia.domain.model.Especie;
import com.projeto.agroecologia.domain.model.EspecieCarac;
import com.projeto.agroecologia.domain.repository.CaracRepository;
import com.projeto.agroecologia.domain.repository.EspecieCaracRepository;
import com.projeto.agroecologia.domain.repository.EspecieRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspecieCaracService {

    private final EspecieCaracRepository especieCaracRepository;
    private final EspecieRepository especieRepository;
    private final CaracRepository caracRepository;

    public List<EspecieCarac> listarTodos() {
        return especieCaracRepository.findAll();
    }

    public List<EspecieCarac> listarPorEspecie(Long especieId) {
        return especieCaracRepository.findByEspecieId(especieId);
    }

    public Optional<EspecieCarac> associar(Long especieId, Long caracId, String valorCarac) {
    Optional<Especie> especieOpt = especieRepository.findById(especieId);
    Optional<Carac> caracOpt = caracRepository.findById(caracId);

    if (especieOpt.isEmpty() || caracOpt.isEmpty()) {
        return Optional.empty();
    }

    boolean exists = especieCaracRepository.existsByEspecieAndCarac(especieOpt.get(), caracOpt.get());
    if (exists) {
        return Optional.empty(); 
    }

    EspecieCarac ec = new EspecieCarac();
    ec.setEspecie(especieOpt.get());
    ec.setCarac(caracOpt.get());
    ec.setValorCarac(valorCarac); 

    EspecieCarac saved = especieCaracRepository.save(ec);
    return Optional.of(saved);
}
}
