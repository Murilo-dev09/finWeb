package com.finweb.finweb.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroNovoLogin(

        @Email
        @NotBlank(message = "E-mail e um campo obrigatorio")
        String email,

        @NotBlank(message = "Senha e um campo obrigatorio")
        String senha){
}
