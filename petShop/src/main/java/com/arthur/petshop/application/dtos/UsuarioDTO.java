package com.arthur.petshop.application.dtos;

import com.arthur.petshop.domain.entitys.Pet;
import com.arthur.petshop.domain.entitys.Usuario;
import com.arthur.petshop.domain.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.*;

public record UsuarioDTO(
        @Schema(description = "Nome do usuario", example = "Pedro")
        String nome,

        @Schema(description = "Data de nascimento do usuario", example = "12-05-2005")
        LocalDate nascimento,

        @Schema(description = "E-mail para contato do usuario", example = "pedro.bertoncelo@gmail.com")
        String email,

        @Schema(description = "Telefone do usuario", example = "934320949")
        String telefone,

        @Schema(description = "Sexo do usuario, definido por enum", example = "M")
        Sexo sexo,

        @Schema(description = "Lista de IDs dos animais de estimação do usuario", example = "[1, 2, 4]")
        List<Long> pets
) {
    public Usuario fromDTO() {
        Usuario usuario = new Usuario();

        usuario.setNome(this.nome);
        usuario.setSexo(this.sexo);
        usuario.setEmail(this.email);
        usuario.setTelefone(this.telefone);
        usuario.setNascimento(this.nascimento);

        return usuario;
    }

    public static UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getNome(),
                usuario.getNascimento(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getSexo(),
                new ArrayList<>(
                        usuario.getPets()
                                .stream()
                                .map(Pet::getId)
                                .toList()
                )
        );
    }
}
