package com.arthur.petshop.application.services;


import com.arthur.petshop.application.dtos.PetDTO;
import com.arthur.petshop.application.dtos.UsuarioDTO;
import com.arthur.petshop.domain.entitys.Pet;
import com.arthur.petshop.domain.entitys.Usuario;
import com.arthur.petshop.infraestructure.repositories.PetRepository;
import com.arthur.petshop.infraestructure.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        Usuario usuario = dto.toEntity();
        usuario.verificarIdade();
        usuarioRepository.save(usuario);
        return UsuarioDTO.fromEntity(usuario);
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        return UsuarioDTO.fromEntity(usuario);
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<PetDTO> listarTodosOsPet(Long id) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if (optUsuario.isPresent()) {
            return optUsuario.get().getPets()
                    .stream()
                    .map(PetDTO::fromEntity)
                    .toList();
        } else {
            throw new EntityNotFoundException("Usuario não encontrado");
        }
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        usuario.setNome(dto.nome());
        usuario.setNascimento(dto.nascimento());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        usuario.setSexo(dto.sexo());

        Usuario atualizado = usuarioRepository.save(usuario);
        return UsuarioDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}