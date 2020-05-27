DROP TABLE seguranca.usuario_papel;

CREATE SEQUENCE seguranca.perfil_sequence START 1;


CREATE TABLE seguranca.perfil
(
    id bigint NOT NULL,
    nome text COLLATE pg_catalog."default",
    CONSTRAINT perfil_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE seguranca.perfil OWNER to administrador;


CREATE TABLE seguranca.usuario_perfil
(
    id_usuario bigint NOT NULL,
    id_perfil bigint NOT NULL,
    CONSTRAINT usuario_perfil_key PRIMARY KEY (id_usuario, id_perfil),
    CONSTRAINT usuario_perfil_perfil_fkey FOREIGN KEY (id_perfil)
        REFERENCES seguranca.perfil (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT usuario_perfil_usuario_fkey FOREIGN KEY (id_usuario)
        REFERENCES administracao.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE seguranca.usuario_perfil OWNER to administrador;


CREATE TABLE seguranca.perfil_papel
(
    id_perfil bigint NOT NULL,
    id_papel bigint NOT NULL,
    CONSTRAINT perfil_papel_key PRIMARY KEY (id_perfil, id_papel),
    CONSTRAINT perfil_papel_perfil_fkey FOREIGN KEY (id_perfil)
        REFERENCES seguranca.perfil (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT perfil_papel_papel_fkey FOREIGN KEY (id_papel)
        REFERENCES seguranca.papel (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE seguranca.perfil_papel OWNER to administrador;

INSERT INTO seguranca.perfil VALUES (nextval('seguranca.perfil_sequence'), 'ADMINISTRADOR_USUARIOS');

INSERT INTO seguranca.papel VALUES (nextval('seguranca.papel_sequence'), 'CADASTRAR_USUARIO');
INSERT INTO seguranca.perfil_papel VALUES (currval('seguranca.perfil_sequence'), currval('seguranca.papel_sequence'));

INSERT INTO seguranca.papel VALUES (nextval('seguranca.papel_sequence'), 'ALTERAR_USUARIO');
INSERT INTO seguranca.perfil_papel VALUES (currval('seguranca.perfil_sequence'), currval('seguranca.papel_sequence'));

INSERT INTO seguranca.papel VALUES (nextval('seguranca.papel_sequence'), 'LISTAR_USUARIO');
INSERT INTO seguranca.perfil_papel VALUES (currval('seguranca.perfil_sequence'), currval('seguranca.papel_sequence'));

INSERT INTO seguranca.usuario_perfil VALUES (1, currval('seguranca.perfil_sequence'));