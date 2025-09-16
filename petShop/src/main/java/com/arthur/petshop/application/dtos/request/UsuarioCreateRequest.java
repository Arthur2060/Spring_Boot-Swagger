package com.arthur.petshop.application.dtos.request;

import com.arthur.petshop.domain.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record UsuarioCreateRequest(
        @Schema(description = "Nome do usuario", example = "Pedro")
        String nome,

        @Schema(description = "Data de nascimento do usuario", example = "12-05-2005")
        LocalDate nascimento,

        @Schema(description = "E-mail para contato do usuario", example = "pedro.bertoncelo@gmail.com")
        String email,

        @Schema(description = "Telefone do usuario", example = "934320949")
        String telefone,

        @Schema(description = "Sexo do usuario, definido por enum", example = "M")
        Sexo sexo
) {
}
