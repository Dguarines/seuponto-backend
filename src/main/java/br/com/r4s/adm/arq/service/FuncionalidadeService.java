package br.com.r4s.adm.arq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.r4s.adm.arq.autenticacao.repository.FuncionalidadeRepository;
import br.com.r4s.adm.arq.dominio.Funcionalidade;
import br.com.r4s.adm.arq.dominio.Perfil;

@Service
public class FuncionalidadeService extends AbstractService<FuncionalidadeRepository, Funcionalidade> implements EntityService<Funcionalidade> {

	public FuncionalidadeService(FuncionalidadeRepository repository) {
		super(repository);
	}

	public List<Funcionalidade> findByPerfil(Perfil perfil) {
		return repository.findByIdPerfil(perfil.getId());
	};

}
