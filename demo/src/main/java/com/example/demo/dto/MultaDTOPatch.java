package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultaDTOPatch {


    @Schema(description = "Valor da multa", example = "4.50")
    private BigDecimal valor;

    @Schema(description = "Código do status da multa: (1) Pendente (2) Paga (3) Cancelada", example = "2")
    private Integer statusCode;

    @Schema(description = "Data do pagamento da multa", example = "2025-05-21T10:00:00")
    private LocalDateTime dataPagamento;

    @Schema(description = "ID do empréstimo relacionado", example = "1")
    private Long emprestimo_id;
}
