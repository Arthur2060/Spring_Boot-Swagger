package com.arthur.petshop.infraestructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter {

    private final JwtService jwtService;
}
