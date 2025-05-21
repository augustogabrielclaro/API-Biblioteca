package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@NoArgsConstructor @AllArgsConstructor
public class MultaDTO {
    @Schema(description = "ID da multa", example = "1")
    private Long id;

    @Schema(description = "Valor da multa", example = "4.50")
    @NotNull(message = "Valor da multa é obrigatório!")
    private BigDecimal valor;

    @Schema(description = "Código do status da multa", example = "(1) Pendente (2) Paga (3) Cancelada")
    @NotNull(message = "Codigo de status da multa é obrigatório!")    
    private Integer statusCode;

    @Schema(description = "Data do pagamento da multa", example = "2025-05-21T10:00:00")
    @NotNull(message = "Data de pagamento é obrigatório!")
    private LocalDateTime dataPagamento;

    @Schema(description = "ID do empréstimo relacionado", example = "1")
    @NotNull(message = "ID do empréstimo é obrigatório")
    private Long emprestimo_id;
}
