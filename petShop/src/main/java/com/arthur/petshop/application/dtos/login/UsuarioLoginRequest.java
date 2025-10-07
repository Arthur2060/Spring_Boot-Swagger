package com.arthur.petshop.application.dtos.login;

public record UsuarioLoginRequest(
        String email,
        String senha
) {
}
