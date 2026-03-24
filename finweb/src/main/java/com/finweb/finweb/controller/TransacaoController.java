package com.finweb.finweb.controller;

import com.finweb.finweb.model.transacao.DadosResumoDashbord;
import com.finweb.finweb.model.transacao.DadosAlterarTransacao;
import com.finweb.finweb.model.transacao.DadosListagemTransacao;
import com.finweb.finweb.model.transacao.DadosNovaTransacao;
import com.finweb.finweb.model.usuario.Usuario;
import com.finweb.finweb.service.TransacaoService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("transacoes")
public class TransacaoController {

    private TransacaoService service;

    public TransacaoController(TransacaoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DadosListagemTransacao> cadastrarTransacao(@RequestBody @Valid DadosNovaTransacao dados, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Usuario logado){
        var transacao = service.criarTransacao(dados, logado.getId());
        var uri =  uriBuilder.path("transacoes/{id}").buildAndExpand(transacao.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(new DadosListagemTransacao(transacao));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTransacao>> listarTransacoes(@ParameterObject @PageableDefault( size = 10, sort = {"data"})Pageable paginacao, @AuthenticationPrincipal Usuario logado){
        return ResponseEntity.ok(service.listarTransacoes(paginacao, logado.getId()));
    }

    @PutMapping
    public ResponseEntity<DadosListagemTransacao> alterarTransacao(@RequestBody @Valid DadosAlterarTransacao dados, @AuthenticationPrincipal Usuario logado){
        var transacao = service.atualizarTransacao(dados, logado.getId());
        return ResponseEntity.ok(new DadosListagemTransacao(transacao));
    }


    @DeleteMapping({"/{transacaoId}/usuario/{usuarioId}"})
    public ResponseEntity<Void> excluirTransacao(@PathVariable Long transacaoId, @PathVariable Long usuarioId){
        service.excluirTransacao(transacaoId, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dashbord")
    public ResponseEntity<DadosResumoDashbord> dashboard(@AuthenticationPrincipal Usuario logado){
        return ResponseEntity.ok(service.resumoDashbord(logado.getId()));
    }
}
