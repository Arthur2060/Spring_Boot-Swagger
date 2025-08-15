package com.arthur.petshop.application.dtos;

import com.arthur.petshop.domain.entitys.Pet;
import com.arthur.petshop.domain.enums.Especies;
import io.swagger.v3.oas.annotations.media.Schema;

public record PetDTO(
        @Schema(description = "Nome do animal", example = "Bob")
        String nome,

        @Schema(description = "Espécie do animal em questão", example = "CACHORRO")
        Especies especie,

        @Schema(description = "Notas opcionais que podem afetar o atendimento veterinario",
                example = "Sem um olho, Sem uma pata, diabético")
        String nota
) {
    public Pet fromDTO() {
        Pet pet = new Pet();

        pet.setNome(nome);
        pet.setNota(nota);
        pet.setEspecie(especie);

        return pet;
    }

    public static PetDTO toDTO(Pet pet) {
        return new PetDTO(
                pet.getNome(),
                pet.getEspecie(),
                pet.getNota()
        );
    }
}
