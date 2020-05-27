package br.com.r4s.adm.arq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.r4s.adm.arq.autenticacao.repository.CaminhoFuncionalidadeRepository;
import br.com.r4s.adm.arq.dominio.CaminhoFuncionalidade;

@Service
public class CaminhoFuncionalidadeService extends AbstractService<CaminhoFuncionalidadeRepository, CaminhoFuncionalidade> implements EntityService<CaminhoFuncionalidade> {

	public CaminhoFuncionalidadeService(CaminhoFuncionalidadeRepository repository) {
		super(repository);
	}

	public List<CaminhoFuncionalidade> findFuncionalidades() {
		return repository.findByFuncionalidadeIsNull();
	};

}
