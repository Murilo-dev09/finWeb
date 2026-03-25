package com.finweb.finweb.controller;

import com.finweb.finweb.model.transacao.DadosListagemTransacao;
import com.finweb.finweb.model.usuario.DadosCadastroNovoLogin;
import com.finweb.finweb.model.usuario.DadosListagemNovoLogin;
import com.finweb.finweb.model.usuario.Usuario;
import com.finweb.finweb.security.DadosLogin;
import com.finweb.finweb.security.DadosTokenJWT;
import com.finweb.finweb.security.TokenService;
import com.finweb.finweb.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final UsuarioService usuarioService;


    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dados){
        var authenticationToken =
                new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT =
                tokenService.gerarToken((Usuario)authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCadastroNovoLogin dados, UriComponentsBuilder uriBuilder){
        var novoUsuario = usuarioService.criarLogin(dados);

        var uri = uriBuilder.path("/login/{id}").buildAndExpand(novoUsuario.getId()).toUri();

        return ResponseEntity.created(uri)
                .body(new DadosListagemNovoLogin(novoUsuario));
    }
}

