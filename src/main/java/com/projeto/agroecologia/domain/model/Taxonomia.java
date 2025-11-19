package com.projeto.agroecologia.domain.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
@Table(name = "taxonomias")
public class Taxonomia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "nivel", nullable = false)
    private String nivel;

    // 🔹 Hierarquia: cada taxonomia pode ter um "pai"
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Taxonomia parent;

    // 🔹 Filhos da taxonomia
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Taxonomia> children = new ArrayList<>();

    // 🔹 Evita loop com Especie
    @OneToOne(mappedBy = "taxonomia", cascade = CascadeType.ALL)
    @JsonIgnore // evita loop Especie -> Taxonomia -> Especie
    private Especie especie;
}
