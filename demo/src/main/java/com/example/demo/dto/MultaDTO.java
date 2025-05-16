package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@NoArgsConstructor @AllArgsConstructor
public class MultaDTO {

    private Long id;

    @NotNull(message = "Valor da multa é obrigatório!")
    private BigDecimal valor;

    @NotBlank(message = "Status da multa é obrigatório!")
    private String status;

    @NotNull(message = "Data de pagamento é obrigatório!")
    private LocalDateTime dataPagamento;

    @NotNull(message = "ID do empréstimo é obrigatório")
    private Long emprestimo_id;
}
