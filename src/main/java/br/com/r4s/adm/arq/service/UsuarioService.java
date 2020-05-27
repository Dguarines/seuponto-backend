package br.com.r4s.adm.arq.service;

import br.com.r4s.adm.arq.dominio.Usuario;

public interface UsuarioService extends EntityService<Usuario> {

    Usuario findByLogin(String username);
}
