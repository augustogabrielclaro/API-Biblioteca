package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class EmprestimoDTOPost {

   @Schema(description = "ID do emprestimo", example = "1")
    private Long id;

    @Schema(description = "Id do Livro relacionado com Empréstimo")
     @NotNull(message = "Id de Livro obrigatoria")
    private Long livroId;

    @Schema(description = "Id do Cliente relacionado com Empréstimo")
    @NotNull(message = "Id do Cliente obrigatoria")
    private Long clienteId;

}
