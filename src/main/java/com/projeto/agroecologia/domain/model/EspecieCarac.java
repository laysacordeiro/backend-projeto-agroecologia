package com.projeto.agroecologia.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EspecieCarac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    
    @Column(name="valor_carac")
    private String valorCarac;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="especie_id")
    private Especie especie;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="carac_id")
    private Carac carac;

}
