create sequence administracao.caminho_funcionalidade_sequence START 1;

CREATE TABLE administracao.caminho_funcionalidade
(
    id bigint NOT NULL,
    id_caminho_funcionalidade_pai bigint,
    id_funcionalidade bigint,
    nome character varying(50) NOT NULL,
    descricao character varying(200) NOT NULL,
    CONSTRAINT caminho_funcionalidade_pkey PRIMARY KEY (id),
    FOREIGN KEY (id_caminho_funcionalidade_pai) REFERENCES administracao.caminho_funcionalidade (id)
)
WITH (
    OIDS = FALSE
);

alter table administracao.caminho_funcionalidade owner to administrador;

create sequence administracao.funcionalidade_sequence START 1;

CREATE TABLE administracao.funcionalidade
(
    id bigint NOT NULL,
    id_papel bigint,
    nome character varying(50) NOT NULL,
    descricao character varying(200) NOT NULL,
    CONSTRAINT funcionalidade_pkey PRIMARY KEY (id),
    FOREIGN KEY (id_papel) REFERENCES seguranca.papel (id)
)
WITH (
    OIDS = FALSE
);

alter table administracao.funcionalidade owner to administrador;