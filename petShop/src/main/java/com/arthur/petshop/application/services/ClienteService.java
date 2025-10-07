package com.arthur.petshop.application.services;


import com.arthur.petshop.application.dtos.request.ClienteCreateRequest;
import com.arthur.petshop.application.dtos.response.PetResponse;
import com.arthur.petshop.application.dtos.response.ClienteResponse;
import com.arthur.petshop.application.mapper.PetMapper;
import com.arthur.petshop.application.mapper.UsuarioMapper;
import com.arthur.petshop.domain.entitys.Cliente;
import com.arthur.petshop.infraestructure.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponse criarUsuario(ClienteCreateRequest dto) {
        Cliente cliente = UsuarioMapper.toEntity(dto);
        cliente.verificarIdade();
        return UsuarioMapper.fromEntity(clienteRepository.save(cliente));
    }

    public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        return UsuarioMapper.fromEntity(cliente);
    }

    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(UsuarioMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public List<PetResponse> listarTodosOsPet(Long id) {
        Optional<Cliente> optUsuario = clienteRepository.findById(id);

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
    public ClienteResponse atualizarUsuario(Long id, ClienteCreateRequest dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        cliente.setNome(dto.nome());
        cliente.setNascimento(dto.nascimento());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setSexo(dto.sexo());

        Cliente atualizado = clienteRepository.save(cliente);
        return UsuarioMapper.fromEntity(atualizado);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}