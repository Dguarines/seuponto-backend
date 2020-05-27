package br.com.r4s.adm.arq.autenticacao.service;

import org.springframework.stereotype.Service;

import br.com.r4s.adm.arq.autenticacao.repository.PerfilRepository;
import br.com.r4s.adm.arq.dominio.Perfil;
import br.com.r4s.adm.arq.service.AbstractService;
import br.com.r4s.adm.arq.service.EntityService;
import br.com.r4s.adm.exception.RegraNegocioException;

@Service
public class PerfilService extends AbstractService<PerfilRepository, Perfil> implements EntityService<Perfil> {
	public PerfilService(PerfilRepository repository) {
		super(repository);
	}

	public boolean verificaNomeDoPerfilExistente(Perfil perfil) {
		return repository.verificaNomePerfilExistente(perfil.getNome(), perfil.getId() != null ? perfil.getId() : 0L);
	}

	public boolean verificaPerfilComOsMesmosPapeis(Perfil perfil) {
		return repository.verificaPerfilComOsMesmosPapeis(perfil.getPapeis(), perfil.getId() != null ? perfil.getId() : 0);
	}

	@Override
	public Perfil save(Perfil novoPerfil) {
		boolean nomeDoPerfilExistente = verificaNomeDoPerfilExistente(novoPerfil);
		if (nomeDoPerfilExistente) {
			throw new RegraNegocioException("rn.perfil.nome_ja_existente", novoPerfil.getNome());
		}

		boolean perfilComOsMesmoPapeis = verificaPerfilComOsMesmosPapeis(novoPerfil);
		if (perfilComOsMesmoPapeis) {
			throw new RegraNegocioException("rn.perfil.perfil_com_mesmo_papeis");
		}
		return super.save(novoPerfil);
	}

	@Override
	public void deleteById(Long id) {
		boolean perfilEmUsoPorUsuario = repository.verificaPerfilEmUsoPorUsuario(id);
		if (perfilEmUsoPorUsuario) {
			throw new RegraNegocioException("rn.perfil.perfil_em_uso_por_usuario");
		}

		super.deleteById(id);
	}
}

