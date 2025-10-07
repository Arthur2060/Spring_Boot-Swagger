package com.arthur.petShop;

import com.arthur.petshop.application.dtos.request.ClienteCreateRequest;
import com.arthur.petshop.application.dtos.response.ClienteResponse;
import com.arthur.petshop.application.mapper.UsuarioMapper;
import com.arthur.petshop.application.services.ClienteService;
import com.arthur.petshop.domain.entitys.Cliente;
import com.arthur.petshop.domain.enums.Sexo;
import com.arthur.petshop.domain.exception.IdadeInvalida;
import com.arthur.petshop.infraestructure.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
	private ClienteService service;

	@Test
	void deveCriarUsuario() {
		ClienteCreateRequest dto = new ClienteCreateRequest(
				"Arthur",
				LocalDate.of(2006, 5, 17),
				"arthur@gmail.com",
				"000000000",
				Sexo.M
		);

		Cliente entidade = UsuarioMapper.toEntity(dto);

		when(repository.save(any())).thenReturn(entidade);

		ClienteResponse salvo = service.criarUsuario(dto);
		assertNotNull(salvo);
	}

	@Test
	void deveSerMaior() {
		ClienteCreateRequest dto = new ClienteCreateRequest(
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