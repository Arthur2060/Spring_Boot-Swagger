package com.projeto.petShop;

import com.arthur.petshop.application.dtos.UsuarioDTO;
import com.arthur.petshop.application.services.UsuarioService;
import com.arthur.petshop.domain.entitys.Usuario;
import com.arthur.petshop.domain.enums.Sexo;
import com.arthur.petshop.infraestructure.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
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
	void deveConterSexoValido() {
		Usuario usuario = new Usuario();

		usuario.setNome("Pedro");
		usuario.setSexo(Sexo.M);
		usuario.setNascimento(LocalDate.of(2000, 05, 12));
		usuario.setTelefone("000000000");
		usuario.setEmail("pedro@gmail.com");

		when(repository.save(usuario)).thenReturn(usuario);
		UsuarioDTO salvo = service.criarUsuario(UsuarioDTO.toDTO(usuario));

		assertNotNull(salvo);

		verify(repository).save(usuario);
	}

	@Test
	void contextLoads() {
	}

}
