package com.finweb.finweb.model.usuario;

public record DadosListagemNovoLogin(
        Long id,
        String email
) {
    public DadosListagemNovoLogin(Usuario usuario) {
        this(usuario.getId(), usuario.getEmail());
    }
}
