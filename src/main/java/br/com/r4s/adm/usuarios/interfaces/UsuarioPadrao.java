package br.com.r4s.adm.usuarios.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioPadrao extends UserDetails {
	
	public Long getId();
	public void setId(Long id);
	public void setLogin(String login);
	public void setSenha(String senha);
	
}
