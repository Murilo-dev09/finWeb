CREATE TABLE transacoes(
        id BIGINT NOT NULL AUTO_INCREMENT,

        descricao VARCHAR(255) NOT NULL,
        valor DECIMAL(10,2) NOT NULL,
        data DATETIME,
        categoria VARCHAR(100) NOT NULL,
        tipoTransacao VARCHAR(100) NOT NULL,

        PRIMARY KEY (id)
);