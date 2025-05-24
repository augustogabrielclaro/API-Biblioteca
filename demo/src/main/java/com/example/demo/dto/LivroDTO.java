package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(
        regexp = "^(978-65-(\\d{1}-\\d{6}|\\d{2}-\\d{5}|\\d{3}-\\d{4})-\\d{1})$",
        message = "O código isbn deve conter o formato ISBN-13"
    )
    private String isbn;

    @NotBlank(message = "Categoria do Livro é obrigatório")
    private String categoria;
}
