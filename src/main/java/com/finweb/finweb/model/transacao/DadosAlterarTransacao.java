package com.finweb.finweb.model.transacao;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAlterarTransacao(
        @NotNull
        Long id,
        String descricao,
        BigDecimal valor,
        CategoriaMovimentacao categoria
) {
}
