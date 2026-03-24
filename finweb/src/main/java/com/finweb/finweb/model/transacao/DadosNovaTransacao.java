package com.finweb.finweb.model.transacao;

import com.finweb.finweb.model.usuario.Usuario;
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
