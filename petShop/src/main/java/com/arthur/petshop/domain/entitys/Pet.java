package com.arthur.petshop.domain.entitys;

import com.arthur.petshop.domain.enums.Especies;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Especies especie;
    private String nota;

    @ManyToOne
    private Usuario dono;
}
