package com.arthur.petshop.interface_ui.controller;

import com.arthur.petshop.application.dtos.request.ClienteCreateRequest;
import com.arthur.petshop.application.dtos.response.PetResponse;
import com.arthur.petshop.application.dtos.response.ClienteResponse;
import com.arthur.petshop.application.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente Controller", description = "Realiza operações referentes a manipulação de dados da entidade cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @GetMapping
    @Operation(summary = "Listar todos os usuarios",
            description = "Faz uma lista de todos os usuarios cadastrados no sistema.")
    public ResponseEntity<List<ClienteResponse>> listarUsuarios() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por id", description = "Retorna um unico usuario referente ao ID buscado")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable Long id) {
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
    public ResponseEntity<ClienteResponse> cadastrarUsuario(@RequestBody ClienteCreateRequest dto) {
        return ResponseEntity.ok(service.criarUsuario(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuario",
            description = "Altera as informações de um usuario no sistema")
    public ResponseEntity<ClienteResponse> atualizarUsuario(@RequestBody ClienteCreateRequest dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizarUsuario(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuario",
            description = "Apaga um usuario do sistema")
    public void deletarUsuario(@PathVariable Long id) {
        service.deletarUsuario(id);
    }
}
