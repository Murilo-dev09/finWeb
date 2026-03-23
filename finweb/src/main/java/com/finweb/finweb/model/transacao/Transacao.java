package com.finweb.finweb.model.transacao;

import com.finweb.finweb.model.transacao.dto.DadosAlterarTransacao;
import com.finweb.finweb.model.transacao.dto.DadosNovaTransacao;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private CategoriaDespesa categoria;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;

    public Transacao(DadosNovaTransacao dados) {
            this.descricao = dados.descricao();
            this.valor = dados.valor();
            this.data = dados.data();
            this.categoria = dados.categoria();
            this.tipoTransacao = dados.tipoTransacao();
    }

    public void alterarTransacao(DadosAlterarTransacao dados) {
        if (dados.descricao() != null){
            this.descricao = dados.descricao();
        }

        if (dados.valor() != null){
            this.valor = dados.valor();
        }

        if (dados.categoria() != null){
            this.categoria = dados.categoria();
        }
    }
}
