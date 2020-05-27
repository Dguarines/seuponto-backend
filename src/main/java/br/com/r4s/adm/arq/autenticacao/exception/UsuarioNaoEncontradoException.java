package br.com.r4s.adm.arq.autenticacao.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = -2072810461571541694L;
	
	public UsuarioNaoEncontradoException(Long id) {
		super("Não foi possível encontrar o usuário " + id);
	}
}
