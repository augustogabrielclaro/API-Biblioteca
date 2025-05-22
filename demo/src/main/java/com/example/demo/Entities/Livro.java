package com.example.demo.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "livros")
@NoArgsConstructor @AllArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "titulo")
    private String titulo;

    @Column(nullable = false, name = "autor")
    private String autor;

    @Column(nullable = false, unique = true, name = "isbn")
    private  String isbn;
    
    @Column(nullable = false, name = "quantidade")
    private int quantidade;

    @Column(nullable = false, name = "categoria")
    private String categoria;
}