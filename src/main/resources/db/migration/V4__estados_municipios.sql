--CRIAÇÃO DOS ESTADOS

CREATE SEQUENCE administracao.unidade_federativa_sequence START 1;

CREATE TABLE administracao.unidade_federativa
(
    id bigint NOT NULL,
    codigo_ibge bigint NOT NULL,
    sigla character varying(2) NOT NULL,
    nome character varying(50) NOT NULL,
    CONSTRAINT unidade_federativa_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE administracao.unidade_federativa
    OWNER to administrador;
COMMENT ON TABLE administracao.unidade_federativa
    IS 'Tabela de unidades federativas, fonte: https://github.com/kelvins/Municipios-Brasileiros';

--FIM DA CRIAÇÃO DOS ESTADOS
--CRIAÇÃO DOS MUNICÍPIOS

CREATE SEQUENCE administracao.municipio_sequence START 1;

CREATE TABLE administracao.municipio (
	id bigint NOT NULL,
    codigo_ibge bigint,
    nome VARCHAR(100) NOT NULL,
    latitude FLOAT(8) NOT NULL,
    longitude FLOAT(8) NOT NULL,
    capital BOOLEAN NOT NULL,
    id_unidade_federativa bigint NOT NULL,
    constraint municipio_pkey PRIMARY KEY (id),
    FOREIGN KEY (id_unidade_federativa) REFERENCES administracao.unidade_federativa (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE administracao.municipio
    OWNER to administrador;
COMMENT ON TABLE administracao.municipio
    IS 'Tabela de unidades federativas, fonte: https://github.com/kelvins/Municipios-Brasileiros';

--FIM CRIAÇÃO MUNICIPIOS

--TABELA ENDEREÇO

CREATE SEQUENCE administracao.endereco_sequence START 1;

CREATE TABLE administracao.endereco
(
    id bigint NOT NULL,
    cep character varying(10),
    logradouro character varying(256),
    complemento character varying(256),
    bairro character varying(256),
    numero character varying(16),
    id_municipio bigint,
    CONSTRAINT endereco_pkey PRIMARY KEY (id),
    FOREIGN KEY (id_municipio) REFERENCES administracao.municipio (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE administracao.endereco OWNER to administrador;

--FIM TABELA ENDEREÇO

--TABELA PESSOA

CREATE SEQUENCE administracao.pessoa_sequence START 1;

CREATE TABLE administracao.pessoa
(
    id bigint NOT NULL,
    nome character varying(256),
    cpf bigint NOT NULL,
    data_nascimento date,
    email character varying(256),
    telefone character varying(11),
    celular character varying(11),
    id_endereco bigint,
    CONSTRAINT pessoa_pkey PRIMARY KEY (id),
    FOREIGN KEY (id_endereco) REFERENCES administracao.endereco (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE administracao.pessoa OWNER to administrador;

--FIM TABELA PESSOA