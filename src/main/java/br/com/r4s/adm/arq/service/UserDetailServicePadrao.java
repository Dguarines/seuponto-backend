package br.com.r4s.adm.arq.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.r4s.adm.arq.autenticacao.repository.PapelRepository;
import br.com.r4s.adm.arq.autenticacao.repository.PerfilRepository;
import br.com.r4s.adm.arq.dominio.Papel;
import br.com.r4s.adm.arq.dominio.Perfil;
import br.com.r4s.adm.arq.dominio.Usuario;
import br.com.r4s.adm.arq.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailServicePadrao implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	private final PerfilRepository perfilRepository;

	private final PapelRepository papelRepository;

	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		usuario.setAuthorities(authorities(usuario));
		return usuario;
	}

	public Collection<? extends GrantedAuthority> authorities(Usuario usuario) {
		return authorities(perfilRepository.findByUsuariosIn(usuario));
	}

	public Collection<? extends GrantedAuthority> authorities(List<Perfil> perfis) {
		Collection<GrantedAuthority> auths = new ArrayList<>();

		for (Perfil perfil: perfis) {
			List<Papel> lista = papelRepository.findByPerfisIn(perfil);

			for (Papel papel: lista) {
				auths.add(new SimpleGrantedAuthority(papel.getNome()));
			}
		}

		return auths;
	}
}
