package com.arthur.petshop.application.services;

import com.arthur.petshop.application.dtos.request.PetCreateRequest;
import com.arthur.petshop.application.dtos.response.PetResponse;
import com.arthur.petshop.application.mapper.PetMapper;
import com.arthur.petshop.domain.entitys.Pet;
import com.arthur.petshop.domain.entitys.Usuario;
import com.arthur.petshop.domain.exception.DonoNaoEncontrado;
import com.arthur.petshop.infraestructure.repositories.PetRepository;
import com.arthur.petshop.infraestructure.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    private final PetRepository petRepository;

    private final UsuarioRepository usuarioRepository;

    public PetService(PetRepository petRepository, UsuarioRepository usuarioRepository) {
        this.petRepository = petRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public PetResponse criarPet(PetCreateRequest dto) {
        Pet pet = PetMapper.toEntity(dto);

        Optional<Usuario> optDono = usuarioRepository.findById(dto.dono());

        if (optDono.isPresent()) {
            pet.setDono(optDono.get());
        } else {
            throw new DonoNaoEncontrado("Usuario referenciado como dono não encontrado!");
        }

        Pet salvo = petRepository.save(pet);
        return PetMapper.fromEntity(salvo);
    }

    public PetResponse buscarPorId(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com ID: " + id));
        return PetMapper.fromEntity(pet);
    }

    public List<PetResponse> listarTodos() {
        return petRepository.findAll()
                .stream()
                .map(PetMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public PetResponse atualizarPet(Long id, PetCreateRequest dto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com ID: " + id));

        pet.setNome(dto.nome());
        pet.setEspecie(dto.especie());
        pet.setNota(dto.nota());

        if (!pet.getDono().getId().equals(dto.dono())) {
            Optional<Usuario> optDono = usuarioRepository.findById(dto.dono());

            if (optDono.isPresent()) {
                pet.setDono(optDono.get());
            } else {
                throw new DonoNaoEncontrado("Usuario referenciado como dono não encontrado!");
            }
        }

        Pet atualizado = petRepository.save(pet);
        return PetMapper.fromEntity(atualizado);
    }

    @Transactional
    public void deletarPet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new EntityNotFoundException("Pet não encontrado com ID: " + id);
        }
        petRepository.deleteById(id);
    }
}
