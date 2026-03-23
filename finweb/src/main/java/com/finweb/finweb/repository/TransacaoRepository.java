package com.finweb.finweb.repository;

import com.finweb.finweb.model.transacao.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
        Page<Transacao> findAllByOrderByDataDesc(Pageable pageable);
}
