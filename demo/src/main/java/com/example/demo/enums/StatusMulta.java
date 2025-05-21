package com.example.demo.enums;

public enum StatusMulta {
    PENDENTE(1, "Pendente"),
    PAGA(2, "Paga"),
    CANCELADA(3, "Cancelada");

    private final int codigo;
    private final String descricao;

    StatusMulta(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusMulta fromCodigo(int codigo) {
        for (StatusMulta status : StatusMulta.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }

        throw new IllegalArgumentException("Código de descrição inválido: " + codigo);
    }
}
