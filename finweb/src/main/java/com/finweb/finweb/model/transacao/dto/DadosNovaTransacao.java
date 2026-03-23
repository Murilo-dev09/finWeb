package com.finweb.finweb.model.transacao.dto;

import com.finweb.finweb.model.transacao.CategoriaDespesa;
import com.finweb.finweb.model.transacao.TipoTransacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosNovaTransacao(

        @NotBlank(message = "Descrição não pode ser vazia!")
        String descricao,

        @NotBlank(message = "Valor é obrigatório!")
        BigDecimal valor,

        LocalDateTime data,

        @NotNull(message = "Escolher categoria é obrigatorio!")
        CategoriaDespesa categoria,

        @NotNull(message = "Escolher tipo de transação é obrigatorio!")
        TipoTransacao tipoTransacao

) {
}
