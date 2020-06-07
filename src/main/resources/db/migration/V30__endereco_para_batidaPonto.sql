ALTER TABLE ponto.batida_ponto
  ADD COLUMN id_endereco bigint;
ALTER TABLE ponto.batida_ponto
  ADD FOREIGN KEY (id_endereco) REFERENCES administracao.endereco (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
  
  
ALTER TABLE administracao.endereco
 ADD COLUMN geocode character varying;
 
 
