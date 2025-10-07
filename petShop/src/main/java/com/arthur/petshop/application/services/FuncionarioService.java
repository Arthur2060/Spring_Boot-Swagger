package com.arthur.petshop.application.services;

import com.arthur.petshop.application.dtos.request.FuncionarioCreateRequest;
import com.arthur.petshop.application.dtos.response.FuncionarioResponse;
import com.arthur.petshop.application.mapper.FuncionarioMapper;
import com.arthur.petshop.domain.entitys.Funcionario;
import com.arthur.petshop.domain.enums.Role;
import com.arthur.petshop.infraestructure.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public List<FuncionarioResponse> listarTodos() {
        return funcionarioRepository.findAll()
                .stream().map(FuncionarioMapper::fromEntity)
                .toList();
    }

    public FuncionarioResponse buscarPorId(Long id) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);

        if (optionalFuncionario.isEmpty()) {
            throw new IllegalArgumentException("ID buscado não encontrado!");
        }

        return FuncionarioMapper.fromEntity(optionalFuncionario.get());
    }

    @Transactional
    public FuncionarioResponse cadastrarFuncionario(FuncionarioCreateRequest request) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(request.nome());
        funcionario.setEmail(request.email());
        funcionario.setSexo(request.sexo());
        funcionario.setTelefone(request.telefone());
        funcionario.setNascimento(request.nascimento());
        funcionario.setRole(Role.FUNCIONARIO);

        return FuncionarioMapper.fromEntity(funcionarioRepository.save(funcionario));
    }

    @Transactional
    public FuncionarioResponse atualizarFuncionario(FuncionarioCreateRequest request, Long id) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);

        if (optionalFuncionario.isEmpty()) {
            throw new IllegalArgumentException("ID buscado não encontrado!");
        }

        Funcionario funcionario = optionalFuncionario.get();

        funcionario.setNome(request.nome());
        funcionario.setEmail(request.email());
        funcionario.setNascimento(request.nascimento());
        funcionario.setNascimento(request.nascimento());
        funcionario.setSexo(request.sexo());

        return FuncionarioMapper.fromEntity(funcionario);
    }

    @Transactional
    public void deletarFuncionario(Long id) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);

        optionalFuncionario.ifPresent(funcionarioRepository::delete);
    }
}
