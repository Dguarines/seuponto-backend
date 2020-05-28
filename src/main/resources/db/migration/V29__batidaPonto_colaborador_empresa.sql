-- Table: administracao.empresa_sequence

CREATE SEQUENCE administracao.empresa_sequence START 1;

CREATE TABLE administracao.empresa
(
  id bigint NOT NULL,
  nome_empresa text NOT NULL,
  razao_social character varying,
  codigo character varying,
  cnpj character varying,
  CONSTRAINT empresa_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE administracao.empresa
  OWNER TO administrador;

 
-- Table: administracao.colaborador_sequence

CREATE SEQUENCE administracao.colaborador_sequence START 1;

CREATE TABLE administracao.colaborador
(
  id bigint NOT NULL,
  id_pessoa bigint,
  id_empresa bigint,
  CONSTRAINT colaborador_pkey PRIMARY KEY (id),
  CONSTRAINT colaborador_id_pessoa_fkey FOREIGN KEY (id_pessoa)
      REFERENCES administracao.endereco (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT colaborador_id_empresa_fkey FOREIGN KEY (id_empresa)
      REFERENCES administracao.empresa (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE administracao.colaborador
  OWNER TO administrador;
 
 
-- schema: ponto.batida_ponto
CREATE SCHEMA ponto
       AUTHORIZATION administrador;

 
-- Table: ponto.batida_ponto
 
CREATE SEQUENCE ponto.batida_ponto_sequence START 1;

CREATE TABLE ponto.batida_ponto
(
  id bigint NOT NULL,
  id_colaborador bigint,
  hora_batida timestamp without time zone,
  CONSTRAINT batida_ponto_pkey PRIMARY KEY (id),
  CONSTRAINT batida_ponto_id_colaborador_fkey FOREIGN KEY (id_colaborador)
      REFERENCES administracao.empresa (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ponto.batida_ponto
  OWNER TO administrador;
