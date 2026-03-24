package com.finweb.finweb.service;

import com.finweb.finweb.model.transacao.DadosResumoDashbord;
import com.finweb.finweb.model.transacao.*;
import com.finweb.finweb.model.usuario.Usuario;
import com.finweb.finweb.repository.TransacaoRepository;
import com.finweb.finweb.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransacaoService {

    private TransacaoRepository transacaoRepository;

    private UsuarioRepository usuarioRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, UsuarioRepository usuarioRepository) {
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Transacao criarTransacao(DadosNovaTransacao dados, Long usuarioId){
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado!"));

        Transacao transacao = new Transacao(dados);
        transacao.setUsuario(usuario);

        return transacaoRepository.save(transacao);
    }

    public Page<DadosListagemTransacao> listarTransacoes(Pageable paginacao, Long usuarioId){
        return transacaoRepository.findByUsuarioIdOrderByDataDesc(usuarioId, paginacao)
                .map(DadosListagemTransacao::new);
    }

    @Transactional
    public Transacao atualizarTransacao(DadosAlterarTransacao dados, Long usuarioId){
        Transacao transacao = transacaoRepository.findById(dados.id())
                .orElseThrow(() -> new RuntimeException("Transacao nao encontrada!"));

        if (!transacao.getUsuario().getId().equals(usuarioId)){
            throw new RuntimeException("Operacao nao permitida!");
        }

        if (dados.descricao() != null){
            transacao.setDescricao(dados.descricao());
        }

        if (dados.valor() != null){
            transacao.setValor(dados.valor());
        }

        if (dados.categoria() != null){
            transacao.setCategoria(dados.categoria());
        }

        return transacaoRepository.save(transacao);
    }

    @Transactional
    public void excluirTransacao(Long transacaoId, Long usuarioId){
        Transacao transacao = transacaoRepository.findById(transacaoId)
                .orElseThrow(() -> new RuntimeException("Transacao nao encontrada!"));

        if (!transacao.getUsuario().getId().equals(usuarioId)){
            throw new RuntimeException("Operacao nao permitida!");
        }

        transacaoRepository.delete(transacao);
    }

    public DadosResumoDashbord resumoDashbord(Long usuarioId){
        BigDecimal receitas = transacaoRepository.somarValoresPorTipoEUsuario(usuarioId, TipoTransacao.RECEITA);
        BigDecimal despesas = transacaoRepository.somarValoresPorTipoEUsuario(usuarioId, TipoTransacao.DESPESA);

        receitas = (receitas != null) ? receitas : BigDecimal.ZERO;
        despesas = (despesas != null) ? despesas : BigDecimal.ZERO;

        BigDecimal saldo = receitas.subtract(despesas);

        return new DadosResumoDashbord(receitas, despesas, saldo);
    }
}
