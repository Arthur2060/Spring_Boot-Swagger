package com.arthur.petshop.application.mapper;

import com.arthur.petshop.application.dtos.request.PetCreateRequest;
import com.arthur.petshop.application.dtos.response.PetResponse;
import com.arthur.petshop.domain.entitys.Pet;

public class PetMapper {

    public static Pet toEntity(PetCreateRequest dto) {
        Pet pet = new Pet();

        pet.setNome(dto.nome());
        pet.setNota(dto.nota());
        pet.setEspecie(dto.especie());

        return pet;
    }

    public static PetResponse fromEntity(Pet pet) {
        return new PetResponse(
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getNota(),
                pet.getDono().getId()
        );
    }
}
