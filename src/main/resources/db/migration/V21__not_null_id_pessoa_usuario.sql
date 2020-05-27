INSERT INTO administracao.pessoa(id, nome, cpf, data_nascimento, email, telefone, celular, id_endereco) 
VALUES (nextval('administracao.pessoa_sequence'), 'Administrador', '06510181452', '1987-01-11', 'admin@seuponto.com.br', null, null, null);

UPDATE administracao.usuario SET id_pessoa=1 WHERE id=1;

ALTER TABLE administracao.usuario ALTER COLUMN id_pessoa SET NOT NULL;
