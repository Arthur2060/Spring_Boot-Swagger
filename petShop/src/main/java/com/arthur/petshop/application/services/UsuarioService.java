package com.arthur.petshop.application.services;


import com.arthur.petshop.application.dtos.request.UsuarioCreateRequest;
import com.arthur.petshop.application.dtos.response.PetResponse;
import com.arthur.petshop.application.dtos.response.UsuarioResponse;
import com.arthur.petshop.application.mapper.PetMapper;
import com.arthur.petshop.application.mapper.UsuarioMapper;
import com.arthur.petshop.domain.entitys.Usuario;
import com.arthur.petshop.infraestructure.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    public UsuarioResponse criarUsuario(UsuarioCreateRequest dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario.verificarIdade();
        return UsuarioMapper.fromEntity(usuarioRepository.save(usuario));
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        return UsuarioMapper.fromEntity(usuario);
    }

    public List<UsuarioResponse> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public List<PetResponse> listarTodosOsPet(Long id) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);

        if (optUsuario.isPresent()) {
            return optUsuario.get().getPets()
                    .stream()
                    .map(PetMapper::fromEntity)
                    .toList();
        } else {
            throw new EntityNotFoundException("Usuario não encontrado");
        }
    }

    @Transactional
    public UsuarioResponse atualizarUsuario(Long id, UsuarioCreateRequest dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        usuario.setNome(dto.nome());
        usuario.setNascimento(dto.nascimento());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        usuario.setSexo(dto.sexo());

        Usuario atualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.fromEntity(atualizado);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}