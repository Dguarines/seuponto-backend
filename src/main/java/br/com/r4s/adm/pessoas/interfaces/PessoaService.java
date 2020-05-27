package br.com.r4s.adm.pessoas.interfaces;

import br.com.r4s.adm.arq.service.EntityService;
import br.com.r4s.adm.pessoas.dominio.Pessoa;

public interface PessoaService extends EntityService<Pessoa>{
	
	public Pessoa findByCpf(String cpf);
	public Boolean jaExistePessoaComEsteCPF(String cpf);
}
