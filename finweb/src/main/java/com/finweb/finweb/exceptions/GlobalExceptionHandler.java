package com.finweb.finweb.exceptions;

import com.finweb.finweb.controller.AutenticacaoController;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest()
                .body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErro500(Exception ex){
        return ResponseEntity.internalServerError().body("Erro interno no servidor");
    }

    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
