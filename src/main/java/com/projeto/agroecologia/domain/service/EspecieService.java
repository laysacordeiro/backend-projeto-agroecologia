package com.projeto.agroecologia.domain.service;

import com.projeto.agroecologia.domain.model.Especie;
import com.projeto.agroecologia.domain.model.Taxonomia;
import com.projeto.agroecologia.domain.repository.EspecieRepository;
import com.projeto.agroecologia.domain.repository.TaxonomiaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EspecieService {

    private final EspecieRepository especieRepository;
    private final TaxonomiaRepository taxonomiaRepository;

    public EspecieService(EspecieRepository especieRepository, TaxonomiaRepository taxonomiaRepository) {
        this.especieRepository = especieRepository;
        this.taxonomiaRepository = taxonomiaRepository;
    }

    /**
     * Lista todas as espécies cadastradas
     */
    public List<Especie> listarTodas() {
        return especieRepository.findAll();
    }

    /**
     * Busca uma espécie pelo ID
     */
    public Optional<Especie> buscarPorId(Long id) {
        return especieRepository.findById(id);
    }

    /**
     * Salva uma nova espécie, incluindo sua hierarquia taxonômica completa
     */
    @Transactional
    public Especie salvar(Especie especie) {
        System.out.println("[ESPECIE-SALVAR] Iniciando salvamento da espécie...");

        Taxonomia taxonomia = especie.getTaxonomia();

        if (taxonomia != null) {
            System.out.println("[ESPECIE-SALVAR] Salvando hierarquia taxonômica...");
            Taxonomia taxonomiaFinal = salvarTaxonomiaRecursiva(taxonomia);
            especie.setTaxonomia(taxonomiaFinal);
            System.out.println("[ESPECIE-SALVAR] Taxonomia final associada: " +
                    taxonomiaFinal.getNome() + " (" + taxonomiaFinal.getNivel() + ")");
        }

        Especie salva = especieRepository.save(especie);
        System.out.println("[ESPECIE-SALVAR] Espécie salva com ID: " + salva.getId());
        return salva;
    }

    private Taxonomia salvarTaxonomiaRecursiva(Taxonomia taxonomia) {
        if (taxonomia == null)
            return null;

        System.out.println("[TAXONOMIA] Salvando nível: " + taxonomia.getNome() + " (" + taxonomia.getNivel() + ")");

        Taxonomia parentSalvo = salvarTaxonomiaRecursiva(taxonomia.getParent());
        taxonomia.setParent(parentSalvo);

        Optional<Taxonomia> existente = taxonomiaRepository
            .findByNomeIgnoreCaseAndNivelIgnoreCase(taxonomia.getNome(), taxonomia.getNivel());

        if (existente.isPresent()) {
            return existente.get();
        }

        Taxonomia salva = taxonomiaRepository.saveAndFlush(taxonomia);
        System.out.println("[TAXONOMIA] Nova taxonomia salva: id=" + salva.getId() + " - " + salva.getNome());
        return salva;
    }

    @Transactional
    public Especie atualizar(Long id, Especie especieAtualizada) {
        return especieRepository.findById(id)
                .map(especie -> {
                    especie.setNome(especieAtualizada.getNome());
                    especie.setNomeCientifico(especieAtualizada.getNomeCientifico());
                    especie.setAno(especieAtualizada.getAno());
                    especie.setDescricao(especieAtualizada.getDescricao());

                    if (especieAtualizada.getTaxonomia() != null) {
                        Taxonomia tax = salvarTaxonomiaRecursiva(especieAtualizada.getTaxonomia());
                        especie.setTaxonomia(tax);
                    }

                    Especie salva = especieRepository.save(especie);
                    System.out.println("[ESPECIE-ATUALIZAR] Espécie atualizada ID: " + salva.getId());
                    return salva;
                })
                .orElseThrow(() -> new RuntimeException("Espécie não encontrada com ID: " + id));
    }

    public void deletar(Long id) {
        System.out.println("[ESPECIE-DELETAR] Deletando espécie ID: " + id);
        especieRepository.deleteById(id);
    }
}