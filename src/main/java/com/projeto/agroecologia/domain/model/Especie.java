package com.projeto.agroecologia.domain.model;

import java.util.List;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "especies")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "nome_cientifico", nullable = false)
    private String nomeCientifico;

    private String ano;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    // 🔹 Relação principal com Taxonomia
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taxonomia_id")
    @JsonIgnoreProperties({"children", "especie"}) 
    private Taxonomia taxonomia;

    @OneToMany(mappedBy = "especie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<EspecieCarac> especieCaracs;
}
