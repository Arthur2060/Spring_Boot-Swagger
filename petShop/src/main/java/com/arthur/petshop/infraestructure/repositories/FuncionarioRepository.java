package com.arthur.petshop.infraestructure.repositories;

import com.arthur.petshop.domain.entitys.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
