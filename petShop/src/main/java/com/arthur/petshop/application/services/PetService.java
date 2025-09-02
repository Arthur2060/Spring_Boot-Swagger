package com.arthur.petshop.application.services;

import com.arthur.petshop.application.dtos.PetDTO;
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
    public PetDTO criarPet(PetDTO dto) {
        Pet pet = dto.toEntity();

        Optional<Usuario> optDono = usuarioRepository.findById(dto.dono());

        if (optDono.isPresent()) {
            pet.setDono(optDono.get());
        } else {
            throw new DonoNaoEncontrado("Usuario referenciado como dono não encontrado!");
        }

        Pet salvo = petRepository.save(pet);
        return PetDTO.fromEntity(salvo);
    }

    public PetDTO buscarPorId(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com ID: " + id));
        return PetDTO.fromEntity(pet);
    }

    public List<PetDTO> listarTodos() {
        return petRepository.findAll()
                .stream()
                .map(PetDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public PetDTO atualizarPet(Long id, PetDTO dto) {
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
        return PetDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletarPet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new EntityNotFoundException("Pet não encontrado com ID: " + id);
        }
        petRepository.deleteById(id);
    }
}
