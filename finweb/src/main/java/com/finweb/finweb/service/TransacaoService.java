package com.finweb.finweb.service;

import com.finweb.finweb.model.transacao.Transacao;
import com.finweb.finweb.model.transacao.dto.DadosAlterarTransacao;
import com.finweb.finweb.model.transacao.dto.DadosListagemTransacao;
import com.finweb.finweb.model.transacao.dto.DadosNovaTransacao;
import com.finweb.finweb.repository.TransacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransacaoService {

    private TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Transacao criarTransacao(DadosNovaTransacao dados){
        var transacao = new Transacao(dados);
        repository.save(transacao);
        return transacao;
    }

    public Page<DadosListagemTransacao> listarTransacoes(Pageable paginacao){
        return repository.findAllByOrderByDataDesc(paginacao)
                .map(DadosListagemTransacao::new);
    }

    @Transactional
    public Transacao atualizarTransacao(DadosAlterarTransacao dados){
        var transacao = repository.getReferenceById(dados.id());
        transacao.alterarTransacao(dados);
        repository.save(transacao);
        return transacao;
    }

    @Transactional
    public void excluirTransacao(Long id){
        repository.deleteById(id);
    }


}
