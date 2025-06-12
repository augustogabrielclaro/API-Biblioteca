package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class MultaDTOPost {
    
    @Schema(description = "ID do empréstimo relacionado", example = "1")
    @NotNull(message = "ID do empréstimo é obrigatório")
    private Long emprestimo_id;
}
