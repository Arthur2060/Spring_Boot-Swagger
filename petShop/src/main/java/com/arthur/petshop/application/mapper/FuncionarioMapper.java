package com.arthur.petshop.application.mapper;

import com.arthur.petshop.application.dtos.request.FuncionarioCreateRequest;
import com.arthur.petshop.application.dtos.response.FuncionarioResponse;
import com.arthur.petshop.domain.entitys.Funcionario;

public class FuncionarioMapper {
    public Funcionario toEntity(FuncionarioCreateRequest request) {
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(request.nome());
        funcionario.setEmail(request.email());
        funcionario.setNascimento(request.nascimento());
        funcionario.setTelefone(request.telefone());
        funcionario.setSexo(request.sexo());

        return funcionario;
    }

    public static FuncionarioResponse fromEntity(Funcionario funcionario) {
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getNascimento(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getSexo()
        );
    }
}
