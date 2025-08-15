package com.arthur.petshop.infraestructure.repositories;

import com.arthur.petshop.domain.entitys.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
