package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LivroDTO {
    private Long id;

    @NotBlank(message = "Titulo do Livro é obrigatório")
    private String titulo;

    @NotBlank(message = "Autor do Livro é obrigatório")
    private String autor;

    @NotBlank(message = "ISBN do Livro é obrigatório")
    private String isbn;

    @NotBlank(message = "Quantidade do Livro é obrigatório")
    private int quantidade;

    @NotBlank(message = "Categoria do Livro é obrigatório")
    private String categoria;
}
