package com.finweb.finweb.model.transacao.dto;

import com.finweb.finweb.model.transacao.CategoriaDespesa;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAlterarTransacao(
        @NotNull
        Long id,
        String descricao,
        BigDecimal valor,
        CategoriaDespesa categoria
) {
}
