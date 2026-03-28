package com.finweb.finweb.model.transacao;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record DadosListagemCategoria(Page<DadosListagemTransacao> listarCategoria, BigDecimal valorTotal) {


}
