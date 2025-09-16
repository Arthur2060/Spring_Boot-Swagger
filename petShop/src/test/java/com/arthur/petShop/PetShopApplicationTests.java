package com.arthur.petShop;

import com.arthur.petshop.application.dtos.request.UsuarioCreateRequest;
import com.arthur.petshop.application.dtos.response.UsuarioResponse;
import com.arthur.petshop.application.mapper.UsuarioMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetShopApplicationTests {
	@Mock
	private UsuarioRepository repository;

	@InjectMocks
	private UsuarioService service;

	@Test
	void deveCriarUsuario() {
		UsuarioCreateRequest dto = new UsuarioCreateRequest(
				"Arthur",
				LocalDate.of(2006, 5, 17),
				"arthur@gmail.com",
				"000000000",
				Sexo.M
		);

		Usuario entidade = UsuarioMapper.toEntity(dto);

		when(repository.save(any())).thenReturn(entidade);

		UsuarioResponse salvo = service.criarUsuario(dto);
		assertNotNull(salvo);
	}

	@Test
	void deveSerMaior() {
		UsuarioCreateRequest dto = new UsuarioCreateRequest(
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
		when(repository.existsById(1L)).thenReturn(true);

		service.deletarUsuario(1L);

		verify(repository).deleteById(1L);
	}
}