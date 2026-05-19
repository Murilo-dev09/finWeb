package com.finweb.finweb.controller;

import com.finweb.finweb.model.usuario.Usuario;
import com.finweb.finweb.service.RelatorioService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/transacoes")
    public ResponseEntity<byte[]> baixarRelatorio(@AuthenticationPrincipal Usuario logado) {
        byte[] pdf = relatorioService.gerarRelatorioTransacoes(logado.getId());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-finweb.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
