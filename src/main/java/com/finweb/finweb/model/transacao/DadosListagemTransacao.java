package com.finweb.finweb.model.transacao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosListagemTransacao(Long id,
                                     String descricao,
                                     BigDecimal valor,
                                     LocalDateTime data,
                                     CategoriaMovimentacao categoria,
                                     TipoTransacao tipoTransacao) {

    public DadosListagemTransacao(Transacao transacao){
        this(transacao.getId(), transacao.getDescricao(), transacao.getValor(),
             transacao.getData(), transacao.getCategoria(), transacao.getTipoTransacao());
    }

}
