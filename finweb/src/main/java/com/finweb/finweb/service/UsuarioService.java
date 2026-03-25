package com.finweb.finweb.service;

import com.finweb.finweb.model.usuario.DadosCadastroNovoLogin;
import com.finweb.finweb.model.usuario.Usuario;
import com.finweb.finweb.repository.UsuarioRepository;
import com.finweb.finweb.security.DadosLogin;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;



    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario criarLogin(DadosCadastroNovoLogin dados) {
        var usuarioExistente = usuarioRepository.findByEmail(dados.email());

        if (usuarioExistente != null) {
            throw new RuntimeException("Este e-mail já está cadastrado no sistema!");
        }

        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        var novoUsuario = new Usuario(dados.email(), senhaCriptografada);
        return usuarioRepository.save(novoUsuario);
    }
}
