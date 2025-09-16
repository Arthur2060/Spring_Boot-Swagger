package com.arthur.petshop.application.dtos.response;

import com.arthur.petshop.domain.enums.Especies;
import io.swagger.v3.oas.annotations.media.Schema;

public record PetResponse(
        Long id,

        @Schema(description = "Nome do animal", example = "Bob")
        String nome,

        @Schema(description = "Espécie do animal em questão", example = "CACHORRO")
        Especies especie,

        @Schema(description = "Notas opcionais que podem afetar o atendimento veterinario",
                example = "Sem um olho, Sem uma pata, diabético")
        String nota,

        @Schema(description = "Numero de Id do usuario dono do Pet",
                example="3")
        Long dono
) {
}
