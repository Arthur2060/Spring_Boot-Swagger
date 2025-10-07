package com.arthur.petshop.application.mapper;

import com.arthur.petshop.application.dtos.request.ClienteCreateRequest;
import com.arthur.petshop.application.dtos.response.ClienteResponse;
import com.arthur.petshop.domain.entitys.Cliente;

public class UsuarioMapper {

    public static Cliente toEntity(ClienteCreateRequest dto) {
        Cliente cliente = new Cliente();

        cliente.setNome(dto.nome());
        cliente.setSexo(dto.sexo());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setNascimento(dto.nascimento());

        return cliente;
    }

    public static ClienteResponse fromEntity(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getNascimento(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getSexo(),
                cliente.getPets()
                        .stream().map(PetMapper::fromEntity)
                        .toList()
        );
    }
}
