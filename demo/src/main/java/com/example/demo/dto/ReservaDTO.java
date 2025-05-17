package com.example.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ReservaDTO {

    private Long id;

    @NotNull(message = "O id do cliente é obrigatório")
    private Long cliente_id;

    @NotNull(message = "O id do livro é obrigatório")
    private Long livro_id;

    @NotNull(message = "A data de reserva é obrigatória")
    private LocalDateTime dataReserva;

    @NotBlank(message = "Status é obrigatório")
    private String status;
}
