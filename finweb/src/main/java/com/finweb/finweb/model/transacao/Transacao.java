package com.finweb.finweb.model.transacao;

import com.finweb.finweb.model.usuario.Usuario;
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
    private CategoriaMovimentacao categoria;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Transacao(DadosNovaTransacao dados) {
            this.descricao = dados.descricao();
            this.valor = dados.valor();
            this.data = dados.data();
            this.categoria = dados.categoria();
            this.tipoTransacao = dados.tipoTransacao();
    }
}
