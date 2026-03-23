package com.finweb.finweb.controller;

import com.finweb.finweb.model.transacao.dto.DadosAlterarTransacao;
import com.finweb.finweb.model.transacao.dto.DadosListagemTransacao;
import com.finweb.finweb.model.transacao.dto.DadosNovaTransacao;
import com.finweb.finweb.service.TransacaoService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public ResponseEntity<DadosListagemTransacao> cadastrarTransacao(@RequestBody @Valid DadosNovaTransacao dados, UriComponentsBuilder uriBuilder){
        var transacao = service.criarTransacao(dados);
        var uri =  uriBuilder.path("transacao/{id}").buildAndExpand(transacao.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(new DadosListagemTransacao(transacao));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTransacao>> listarTransacoes(@ParameterObject @PageableDefault(size = 10, sort = {"data"})Pageable paginacao){
        return ResponseEntity.ok(service.listarTransacoes(paginacao));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemTransacao> alterarTransacao(@RequestBody @Valid DadosAlterarTransacao dados){
        var transacao = service.atualizarTransacao(dados);
        return ResponseEntity.ok(new DadosListagemTransacao(transacao));
    }


    @DeleteMapping({"/{id}"})
    @Transactional
    public ResponseEntity<Void> excluirTransacao(@PathVariable Long id){
        service.excluirTransacao(id);
        return ResponseEntity.noContent().build();
    }
}
