package br.com.r4s.adm.usuarios.service;

import org.springframework.stereotype.Service;

import br.com.r4s.adm.arq.dominio.Usuario;
import br.com.r4s.adm.arq.repository.UsuarioRepository;
import br.com.r4s.adm.arq.service.AbstractService;
import br.com.r4s.adm.arq.service.UsuarioService;
import br.com.r4s.adm.exception.RegraNegocioException;

@Service
public class UsuarioPadraoService extends AbstractService<UsuarioRepository, Usuario> implements UsuarioService {
	
	public UsuarioPadraoService(UsuarioRepository repository) {
		super(repository);
	}

	@Override
	public Usuario findByLogin(String username) {
		return repository.findByLogin(username);
	}

	@Override
	public Usuario save(Usuario u) {
		if (this.repository.existeUsuarioComMesmoLogin(u.getLogin(), u.getId())) {
			throw new RegraNegocioException("rn.usuario.login_ja_existe");
		}
		if (this.repository.existeUsuarioComCPFInformado(u.getId(), u.getPessoa().getCpf())) {
			throw new RegraNegocioException("rn.usuario.cpf_ja_existente");
		}
		return super.save(u);
	}	
}
