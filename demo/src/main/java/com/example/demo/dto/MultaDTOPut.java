package com.example.demo.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MultaDTOPut {
    @Schema(description = "Código do status da multa: (1) Pendente (2) Paga (3) Cancelada", example = "1")
    @NotNull(message = "Código de status da multa é obrigatório!")    
    private Integer statusCode;

    @Schema(description = "Data do pagamento da multa", example = "2025-05-21T10:00:00")
    @NotNull(message = "Data de pagamento é obrigatório!")
    private LocalDateTime dataPagamento;

    @Schema(description = "ID do empréstimo relacionado", example = "1")
    @NotNull(message = "ID do empréstimo é obrigatório")
    private Long emprestimo_id;
}
