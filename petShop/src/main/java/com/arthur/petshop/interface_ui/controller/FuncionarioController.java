package com.arthur.petshop.interface_ui.controller;

import com.arthur.petshop.application.dtos.request.FuncionarioCreateRequest;
import com.arthur.petshop.application.dtos.response.FuncionarioResponse;
import com.arthur.petshop.application.services.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Funcionario")
@RequiredArgsConstructor
@Tag(name = "Funcionario Controller", description = "Realiza operações referentes a manipulação de dados da entidade funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> listarTodos() {
        return ResponseEntity.status(200).body(funcionarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(funcionarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> cadastrarFuncionario(@RequestBody FuncionarioCreateRequest request) {
        return ResponseEntity.status(201).body(funcionarioService.cadastrarFuncionario(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> atualizarFuncionario(@RequestBody FuncionarioCreateRequest request, @PathVariable Long id) {
        return ResponseEntity.status(201).body(funcionarioService.atualizarFuncionario(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.status(204).build();
    }
}
