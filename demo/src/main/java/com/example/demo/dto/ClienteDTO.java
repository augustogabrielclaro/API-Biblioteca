package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ClienteDTO {

    private long id;

    @NotBlank(message = "Campo nome obrigatorio")
    @Size(min = 2, max = 100)
    private String nome;

    @NotBlank(message = "Campo email obrigatorio")
    @Email
    private String email;

    @NotBlank(message = "Telefone obrigatorio para contato")
    private String telefone;

    @NotBlank(message = "Campo endereco obrigatorio")
    private String endereco;

}

    
    

