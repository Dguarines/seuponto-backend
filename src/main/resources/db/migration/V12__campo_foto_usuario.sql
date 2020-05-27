ALTER TABLE administracao.usuario
ADD COLUMN id_pessoa bigint;
ALTER TABLE administracao.usuario
ADD FOREIGN KEY (id_pessoa) REFERENCES administracao.pessoa (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE administracao.usuario ADD COLUMN foto TEXT;