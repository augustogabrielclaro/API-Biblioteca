package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }
}

    
    

