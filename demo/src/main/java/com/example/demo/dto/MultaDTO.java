package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor @AllArgsConstructor
public class MultaDTO {

    private Long id;

    @NotBlank(message = "Valor da multa é obrigatório!")
    private BigDecimal valor;

    @NotBlank(message = "Status da multa é obrigatório!")
    private String status;

    @NotBlank(message = "Data de pagamento é obrigatório!")
    private LocalDateTime dataPagamento;

    @NotBlank(message = "ID do empréstimo é obrigatório")
    private Long emprestimo_id;
}
