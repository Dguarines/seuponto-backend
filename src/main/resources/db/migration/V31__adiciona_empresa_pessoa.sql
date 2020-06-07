ALTER TABLE administracao.pessoa
  DROP COLUMN empresa;
ALTER TABLE administracao.pessoa
  ADD COLUMN id_empresa bigint;
ALTER TABLE administracao.pessoa
  ADD FOREIGN KEY (id_empresa) REFERENCES administracao.empresa (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
