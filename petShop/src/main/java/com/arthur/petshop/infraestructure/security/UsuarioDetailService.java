package com.arthur.petshop.infraestructure.security;

import com.arthur.petshop.domain.entitys.Cliente;
import com.arthur.petshop.domain.entitys.Usuario;
import com.arthur.petshop.infraestructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioDetailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("E-mail não cadastrado no sistema!"));

        return new User(
                usuario.getEmail(),
                usuario.getNome(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getClass().getName()))
        );
    }
}
