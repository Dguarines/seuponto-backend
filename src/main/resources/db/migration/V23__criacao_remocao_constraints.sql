ALTER TABLE administracao.pessoa
    ADD CONSTRAINT cpf_unique UNIQUE (cpf);

ALTER TABLE administracao.usuario
    ADD CONSTRAINT login_unique UNIQUE (login);