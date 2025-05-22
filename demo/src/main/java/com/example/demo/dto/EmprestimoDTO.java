package com.example.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class EmprestimoDTO {

    private Long id;

    @NotBlank(message = "Data de Emprestimo obrigatoria")
    private LocalDateTime dataEmprestimo;

    
    @NotBlank(message = "Data de Devolução obrigatoria")
    private LocalDateTime dataDevolucao;
    
    @NotBlank(message = "Campo Status Obrigatoria")
    private String status;

    @NotBlank(message = "id livro obrigatoria")
    private Long livroId;

    @NotBlank(message = "Id Cliente obrigatoria")
    private Long clienteId;


}
