ALTER TABLE transacoes ADD column usuario_id BIGINT;

ALTER TABLE transacoes ADD CONSTRAINT fk_transacoes_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id);