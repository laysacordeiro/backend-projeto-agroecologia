package com.projeto.agroecologia.domain.service;

import com.projeto.agroecologia.domain.model.Taxonomia;
import com.projeto.agroecologia.domain.repository.TaxonomiaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaxonomiaService {

    private final TaxonomiaRepository taxonomiaRepository;

    public TaxonomiaService(TaxonomiaRepository taxonomiaRepository) {
        this.taxonomiaRepository = taxonomiaRepository;
    }

    public List<Taxonomia> listarTodas() {
        return taxonomiaRepository.findAll();
    }

    public Optional<Taxonomia> buscarPorId(Long id) {
        return taxonomiaRepository.findById(id);
    }

    public Taxonomia salvar(Taxonomia taxonomia) {
        return taxonomiaRepository.save(taxonomia);
    }

    public Taxonomia atualizar(Long id, Taxonomia novaTaxonomia) {
        return taxonomiaRepository.findById(id)
                .map(t -> {
                    t.setNome(novaTaxonomia.getNome());
                    t.setNivel(novaTaxonomia.getNivel());
                    t.setParent(novaTaxonomia.getParent());
                    return taxonomiaRepository.save(t);
                })
                .orElseThrow(() -> new RuntimeException("Taxonomia não encontrada com ID: " + id));
    }

    public void deletar(Long id) {
        taxonomiaRepository.deleteById(id);
    }

    public List<Long> buscarIdsDescendentes(Long taxonomiaId) {
        Taxonomia raiz = taxonomiaRepository.findById(taxonomiaId)
                .orElseThrow(() -> new RuntimeException("Taxonomia não encontrada com ID: " + taxonomiaId));
        List<Long> ids = new ArrayList<>();
        coletarIdsDescendentes(raiz, ids);
        return ids;
    }

    private void coletarIdsDescendentes(Taxonomia taxonomia, List<Long> ids) {
        ids.add(taxonomia.getId());
        if (taxonomia.getChildren() != null) {
            for (Taxonomia filho : taxonomia.getChildren()) {
                coletarIdsDescendentes(filho, ids);
            }
        }
    }

}
