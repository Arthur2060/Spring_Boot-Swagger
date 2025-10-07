package com.arthur.petshop.interface_ui.controller;

import com.arthur.petshop.application.dtos.request.ClienteCreateRequest;
import com.arthur.petshop.application.dtos.response.PetResponse;
import com.arthur.petshop.application.dtos.response.ClienteResponse;
import com.arthur.petshop.application.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    @Operation(summary = "Listar todos os clientes",
            description = "Faz uma lista de todos os clientes cadastrados no sistema.")
    public ResponseEntity<List<ClienteResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por id", description = "Retorna um unico cliente referente ao ID buscado")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/{id}/pet")
    @Operation(summary = "Listar pets por id", description = "Retorna todos os pets pertencentes aquele cliente")
    public ResponseEntity<List<PetResponse>> listarPetsPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarTodosOsPet(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar cliente",
            description = "Cadastra um novo cliente no sistema")
    public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody ClienteCreateRequest dto) {
        return ResponseEntity.ok(service.criarCliente(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente",
            description = "Altera as informações de um cliente no sistema")
    public ResponseEntity<ClienteResponse> atualizarCliente(@RequestBody ClienteCreateRequest dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizarCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar cliente",
            description = "Apaga um cliente do sistema")
    public void deletarCliente(@PathVariable Long id) {
        service.deletarCliente(id);
    }
}
