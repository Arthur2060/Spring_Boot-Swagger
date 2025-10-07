package com.arthur.petshop.infraestructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UsuarioDetailService usuarioDetailService;
}
