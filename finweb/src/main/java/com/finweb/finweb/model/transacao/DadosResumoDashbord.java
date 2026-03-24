package com.finweb.finweb.model.transacao;

import java.math.BigDecimal;

public record DadosResumoDashbord(
        BigDecimal totalReceitas,
        BigDecimal totalDespesas,
        BigDecimal saldo
) {
}
