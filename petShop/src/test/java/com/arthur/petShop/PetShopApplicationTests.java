package com.arthur.petShop;

import com.arthur.petshop.application.dtos.UsuarioDTO;
import com.arthur.petshop.application.services.UsuarioService;
import com.arthur.petshop.domain.entitys.Usuario;
import com.arthur.petshop.domain.enums.Sexo;
import com.arthur.petshop.domain.exception.IdadeInvalida;
import com.arthur.petshop.infraestructure.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopApplicationTests {
	@Mock
	private UsuarioRepository repository;

	@InjectMocks
	private UsuarioService service;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveCriarUsuario() {
		UsuarioDTO dto = new UsuarioDTO(
				"Arthur",
				LocalDate.of(2006, 5, 17),
				"arthur@gmail.com",
				"000000000",
				Sexo.M
		);

		UsuarioDTO salvo = service.criarUsuario(dto);
		assertNotNull(salvo);
	}

	@Test
	void deveSerMaior() {
		UsuarioDTO dto = new UsuarioDTO(
				"Pedro",
				LocalDate.of(2020, 5, 17),
				"pedro@gmail.com",
				"000000000",
				Sexo.M
		);

		assertThrows(IdadeInvalida.class, () -> service.criarUsuario(dto));
	}

	@Test
	void deletarUsuario() {
		UsuarioDTO dto = new UsuarioDTO(
				"Pedro",
				LocalDate.of(2006, 5, 17),
				"pedro@gmail.com",
				"000000000",
				Sexo.M
		);

		UsuarioDTO salvo = service.criarUsuario(dto);
		assertNotNull(salvo);

		service.deletarUsuario(salvo.id());

		assertThrows(EntityNotFoundException.class, () -> service.buscarPorId());
	}
}