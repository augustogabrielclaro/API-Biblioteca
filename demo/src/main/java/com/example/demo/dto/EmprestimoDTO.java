package com.example.demo.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoDTO {

    @Schema(description = "Id do Emprestimo")
    private Long id;

    @Schema(description = "Data do emprestimo realizado")
    @NotNull(message = "Data de empréstimo obrigatoria")
    private LocalDateTime dataEmprestimo;

    @Schema(description = "Data de Devolução do empréstimo")
    @NotNull(message = "Data de Devolução obrigatoria")
    private LocalDateTime dataDevolucao;

    @Schema(description = "Código do status do empréstimo", example = "(1) Em Andamento (2) Concluído (3) Atrasado")
    @NotNull(message = "Campo Status Obrigatoria")
    private Integer statusCode;

    @Schema(description = "Id do Livro relacionado com Empréstimo")
    @NotNull(message = "id livro obrigatoria")
    private Long livroId;

    @Schema(description = "Id do Cliente relacionado com Empréstimo")
    @NotNull(message = "Id Cliente obrigatoria")
    private Long clienteId;

}
