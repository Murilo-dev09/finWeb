package com.finweb.finweb.model.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private TipoTrasacao tipoTransacao;
}
