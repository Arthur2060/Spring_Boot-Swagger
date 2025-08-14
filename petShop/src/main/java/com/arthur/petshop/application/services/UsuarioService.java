package com.arthur.petshop.application.services;


import com.arthur.petshop.application.dtos.UsuarioDTO;
import com.arthur.petshop.domain.entitys.Usuario;
import com.arthur.petshop.infraestructure.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        Usuario usuario = dto.fromDTO();
        Usuario salvo = repository.save(usuario);
        return UsuarioDTO.toDTO(salvo);
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        return UsuarioDTO.toDTO(usuario);
    }

    public List<UsuarioDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(UsuarioDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        usuario.setNome(dto.nome());
        usuario.setNascimento(dto.nascimento());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());
        usuario.setSexo(dto.sexo());

        Usuario atualizado = repository.save(usuario);
        return UsuarioDTO.toDTO(atualizado);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}