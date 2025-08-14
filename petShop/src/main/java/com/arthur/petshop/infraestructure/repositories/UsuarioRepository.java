package com.arthur.petshop.infraestructure.repositories;

import com.arthur.petshop.domain.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
