package com.example.demo.Entities;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.enums.StatusEmprestimo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "emprestimos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clientesId")
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "livroId")
    @JsonIgnore
    private Livro livro;

    @Column(nullable = false)
    private LocalDateTime dataEmprestimo;

    @Column(nullable = false)
    private LocalDateTime dataDevolucao;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @OneToMany(mappedBy = "emprestimo")
    private List<Multa> multas;

}
