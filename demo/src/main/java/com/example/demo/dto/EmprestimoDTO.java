package com.example.demo.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoDTO {

    private Long id;

    @NotBlank(message = "Data de Emprestimo obrigatoria")
    private LocalDateTime dataEmprestimo;

    @NotBlank(message = "Data de Devolução obrigatoria")
    private LocalDateTime dataDevolucao;

    @Schema(description = "Código do status do emprestimo", example = "(1) Em Andamento (2) Concluído (3) Atrasado")
    @NotBlank(message = "Campo Status Obrigatoria")
    private Integer statusCode;

    @NotBlank(message = "id livro obrigatoria")
    private Long livroId;

    @NotBlank(message = "Id Cliente obrigatoria")
    private Long clienteId;

}
