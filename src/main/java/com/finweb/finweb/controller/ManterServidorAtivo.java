package com.finweb.finweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("manter")
public class ManterServidorAtivo {

    @GetMapping
    public String manterAtivo(){
        return "Ativo";
    }
}