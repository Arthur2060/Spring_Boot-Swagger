package com.arthur.petshop.interface_ui.controller;

import com.arthur.petshop.application.dtos.request.UsuarioCreateRequest;
import com.arthur.petshop.application.dtos.response.PetResponse;
import com.arthur.petshop.application.dtos.response.UsuarioResponse;
import com.arthur.petshop.application.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario Controller", description = "Realiza operações referentes a manipulação de dados da entidade usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    @Operation(summary = "Listar todos os usuarios",
            description = "Faz uma lista de todos os usuarios cadastrados no sistema.")
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por id", description = "Retorna um unico usuario referente ao ID buscado")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/{id}/pet")
    @Operation(summary = "Listar pets por id", description = "Retorna todos os pets pertencentes aquele usuario")
    public ResponseEntity<List<PetResponse>> listarPetsPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarTodosOsPet(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuario",
            description = "Cadastra um novo usuario no sistema")
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(@RequestBody UsuarioCreateRequest dto) {
        return ResponseEntity.ok(service.criarUsuario(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuario",
            description = "Altera as informações de um usuario no sistema")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@RequestBody UsuarioCreateRequest dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizarUsuario(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuario",
            description = "Apaga um usuario do sistema")
    public void deletarUsuario(@PathVariable Long id) {
        service.deletarUsuario(id);
    }
}
