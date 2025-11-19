package com.projeto.agroecologia.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.agroecologia.domain.model.Carac;
import com.projeto.agroecologia.domain.repository.CaracRepository;
@Service
public class CaracService {

    @Autowired
    private CaracRepository caracRepository;

    public List<Carac> findAll() {
        return caracRepository.findAll();
    }

    public Optional<Carac> findById(Long id) {
        return caracRepository.findById(id);
    }

    public Carac save(Carac carac) {
        return caracRepository.save(carac);
    }

    public void deleteById(Long id) {
        caracRepository.deleteById(id);
    }
}