CREATE TABLE transacoes(
        id BIGINT NOT NULL AUTO_INCREMENT,

        descricao VARCHAR(255) NOT NULL,
        valor DECIMAL(10,2) NOT NULL,
        data DATETIME NOT NULL,
        categoria VARCHAR(100) NOT NULL,
        tipo_transacao VARCHAR(100) NOT NULL,
        usuario_id BIGINT,

        CONSTRAINT fk_transacoes_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),

        PRIMARY KEY (id)
);
