package com.arthur.petshop.application.services;

import com.arthur.petshop.application.dtos.PetDTO;
import com.arthur.petshop.application.dtos.UsuarioDTO;
import com.arthur.petshop.domain.entitys.Pet;
import com.arthur.petshop.domain.entitys.Usuario;
import com.arthur.petshop.domain.exception.DonoNaoEncontrado;
import com.arthur.petshop.infraestructure.repositories.PetRepository;
import com.arthur.petshop.infraestructure.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    private PetRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public PetDTO criarPet(PetDTO dto) {
        Pet pet = dto.fromDTO();

        Optional<Usuario> optDono = usuarioRepository.findById(dto.dono());

        if (optDono.isPresent()) {
            pet.setDono(optDono.get());
        } else {
            throw new DonoNaoEncontrado("Usuario referenciado como dono não encontrado!");
        }

        Pet salvo = repository.save(pet);
        return PetDTO.toDTO(salvo);
    }

    public PetDTO buscarPorId(Long id) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com ID: " + id));
        return PetDTO.toDTO(pet);
    }

    public List<PetDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(PetDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PetDTO atualizarPet(Long id, PetDTO dto) {
        Pet pet = repository.findById(id)
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

        Pet atualizado = repository.save(pet);
        return PetDTO.toDTO(atualizado);
    }

    @Transactional
    public void deletarPet(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Pet não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}
