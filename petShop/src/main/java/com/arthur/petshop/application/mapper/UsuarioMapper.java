package com.arthur.petshop.application.mapper;

import com.arthur.petshop.application.dtos.request.UsuarioCreateRequest;
import com.arthur.petshop.application.dtos.response.UsuarioResponse;
import com.arthur.petshop.domain.entitys.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioCreateRequest dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.nome());
        usuario.setSexo(dto.sexo());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        usuario.setNascimento(dto.nascimento());

        return usuario;
    }

    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getNascimento(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getSexo()
        );
    }
}
