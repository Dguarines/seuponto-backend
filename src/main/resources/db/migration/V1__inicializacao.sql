----------------------------------------SCHEMAS----------------------------------------
-- SCHEMA: seguranca

-- DROP SCHEMA seguranca ;

CREATE SCHEMA seguranca AUTHORIZATION administrador;
GRANT ALL ON SCHEMA seguranca TO administrador;

-- SCHEMA: administracao

-- DROP SCHEMA administracao ;

CREATE SCHEMA administracao AUTHORIZATION administrador;
GRANT ALL ON SCHEMA administracao TO administrador;

----------------------------------------SEQUENCES----------------------------------------

CREATE SEQUENCE seguranca.papel_sequence START 1;

CREATE SEQUENCE administracao.usuario_sequence START 1;

----------------------------------------TABLES----------------------------------------

-- Table: seguranca.papel

-- DROP TABLE seguranca.papel;

CREATE TABLE seguranca.papel
(
    id bigint NOT NULL,
    nome text COLLATE pg_catalog."default",
    CONSTRAINT papel_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE seguranca.papel OWNER to administrador;

-- Table: administracao.usuario

-- DROP TABLE administracao.usuario;

CREATE TABLE administracao.usuario
(
    id bigint NOT NULL,
    login text COLLATE pg_catalog."default",
    senha text COLLATE pg_catalog."default",
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE administracao.usuario OWNER to administrador;

-- Table: seguranca.usuario_papel

-- DROP TABLE seguranca.usuario_papel;

CREATE TABLE seguranca.usuario_papel
(
    id_usuario bigint NOT NULL,
    id_papel bigint NOT NULL,
    CONSTRAINT usuario_papel_key PRIMARY KEY (id_usuario, id_papel),
    CONSTRAINT usuario_papel_papel_fkey FOREIGN KEY (id_papel)
        REFERENCES seguranca.papel (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT usuario_papel_usuario_fkey FOREIGN KEY (id_usuario)
        REFERENCES administracao.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE seguranca.usuario_papel OWNER to administrador;