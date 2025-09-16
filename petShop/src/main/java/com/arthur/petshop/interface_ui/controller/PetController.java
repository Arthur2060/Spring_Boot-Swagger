package com.arthur.petshop.interface_ui.controller;

import com.arthur.petshop.application.dtos.request.PetCreateRequest;
import com.arthur.petshop.application.dtos.response.PetResponse;
import com.arthur.petshop.application.services.PetService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@Tag(name = "Pet Controller", description = "Realiza operações referentes ao CRUD da entidade Pet")
public class PetController {
    @Autowired
    private PetService service;

    @GetMapping
    @Operation(summary = "Listar pet", description = "Retorna uma lista de todos os pets cadastrados no sistema!")
    public ResponseEntity<List<PetResponse>> listarPets() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por pet por id", description = "Retorna um pet especifico com o id referente")
    public ResponseEntity<PetResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo pet", description = "Adiciona um novo pet ao sistema")
    public ResponseEntity<PetResponse> cadastrarPet(@RequestBody PetCreateRequest dto) {
        return ResponseEntity.ok(service.criarPet(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pet", description = "Altera as informações de um pet")
    public ResponseEntity<PetResponse> atualizarPet(@PathVariable Long id, @RequestBody PetCreateRequest dto) {
        return ResponseEntity.ok(service.atualizarPet(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pet", description = "Deleta um pet do sistema")
    public ResponseEntity<String> deletarPet(@PathVariable Long id) {
        service.deletarPet(id);

        return ResponseEntity.ok("Pet deletado com sucesso!");
    }
}
