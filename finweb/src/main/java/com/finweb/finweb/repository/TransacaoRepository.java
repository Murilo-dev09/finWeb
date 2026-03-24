package com.finweb.finweb.repository;

import com.finweb.finweb.model.transacao.TipoTransacao;
import com.finweb.finweb.model.transacao.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

        Page<Transacao> findByUsuarioId(Long usuarioId, Pageable paginacao);

        Page<Transacao> findByUsuarioIdAndTipoTransacao(Long usuarioId, TipoTransacao tipoTransacao, Pageable paginacao);

        Page<Transacao> findByUsuarioIdOrderByDataDesc(Long usuarioId, Pageable paginacao);

        @Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.usuario.id = :usuarioId AND t.tipoTransacao = :tipoTransacao")
        BigDecimal somarValoresPorTipoEUsuario(@Param("usuarioId") Long usuarioId, @Param("tipoTransacao") TipoTransacao tipoTransacao);


}
