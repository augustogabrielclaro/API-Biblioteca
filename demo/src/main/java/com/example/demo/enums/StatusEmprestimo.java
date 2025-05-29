package com.example.demo.enums;

public enum StatusEmprestimo {
    EM_ANDAMENTO(1, "Em_andamento"),
    CONCLUIDO(2, "Concluido"),
    ATRASADO(3, "Atrasado");

    private final int codigo;
    private final String descricao;

    StatusEmprestimo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusEmprestimo fromCodigo(int codigo) {
        for (StatusEmprestimo status : StatusEmprestimo.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }

        throw new IllegalArgumentException("Código de descrição inválido: " + codigo);
    }
}



