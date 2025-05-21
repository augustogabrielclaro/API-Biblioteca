package com.example.demo.Entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.demo.enums.StatusMulta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "multas") @Data
@AllArgsConstructor @NoArgsConstructor
public class Multa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusMulta status;
    
    private LocalDateTime dataPagamento;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    @JsonIgnore
    private Emprestimo emprestimo;
}
