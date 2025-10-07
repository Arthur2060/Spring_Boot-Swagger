package com.arthur.petshop.infraestructure.repositories;

import com.arthur.petshop.domain.entitys.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
