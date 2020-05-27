package br.com.r4s.adm.arq.autenticacao.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.r4s.adm.arq.autenticacao.repository.PapelRepository;
import br.com.r4s.adm.arq.dominio.Papel;
import br.com.r4s.adm.arq.service.AbstractService;
import br.com.r4s.adm.arq.service.EntityService;

@Service
public class PapelService extends AbstractService<PapelRepository, Papel> implements EntityService<Papel> {
	public PapelService(PapelRepository repository) {
		super(repository);
	}
	
	public boolean verificaSeTodosPapeisExistem(Collection<Papel> collection) {
		List<Papel> papeis = repository.findPapeisByNomes(collection);
		return collection.size() == papeis.size();
	}
}