package com.finweb.finweb.model.transacao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosNovaTransacao(

        @NotBlank(message = "Descrição não pode ser vazia!")
        String descricao,

        @NotNull(message = "Valor é obrigatório!")
        @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero!")
        BigDecimal valor,

        @NotNull(message = "Data é obrigatório!")
        LocalDateTime data,

        @NotNull(message = "Escolher categoria é obrigatorio!")
        CategoriaMovimentacao categoria,

        @NotNull(message = "Escolher tipo de transação é obrigatorio!")
        TipoTransacao tipoTransacao
) {
}
